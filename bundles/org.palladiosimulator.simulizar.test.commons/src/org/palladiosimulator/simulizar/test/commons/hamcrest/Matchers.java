package org.palladiosimulator.simulizar.test.commons.hamcrest;

import java.util.Arrays;
import java.util.LinkedHashMap;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import javax.measure.unit.Unit;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.TypeSafeMatcher;

public final class Matchers {
    private Matchers() {
    }

    public static <T> Matcher<T> firstMeasurementMatchesDifferentlyThanTheRest(Matcher<T> firstValue,
            Matcher<T> remainderValue) {
        return new BaseMatcher<T>() {
            boolean firstElementHasBeenSeen = false;

            @Override
            public boolean matches(Object actual) {
                var matches = firstElementHasBeenSeen ? remainderValue.matches(actual) : firstValue.matches(actual);
                firstElementHasBeenSeen |= matches;
                return matches;
            }

            @Override
            public void describeTo(Description description) {
                if (firstElementHasBeenSeen) {
                    description.appendText("all but first element match ");
                    remainderValue.describeTo(description);
                } else {
                    description.appendText("first element matches ");
                    firstValue.describeTo(description);
                }
            }
        };
    }
    
    public static <Q extends Quantity> Matcher<Measure<?, Q>> asDoubleIn(Unit<Q> unit, Matcher<? super Double> contentMatcher) {
        return new TypeSafeMatcher<>(Measure.class) {

            @Override
            public void describeTo(Description description) {
                contentMatcher.describeTo(description);
                description.appendText(unit.toString() + " ");
                
            }

            @Override
            protected boolean matchesSafely(Measure<?, Q> item) {
                return contentMatcher.matches(item.doubleValue(unit));
            }
        };
    }
    
    public static <T> BucketMatcher<T> withProbability(Matcher<Double> probabilityMatcher, Matcher<? super T> bucketMatcher) {
        return new BucketMatcher<>(probabilityMatcher, bucketMatcher);
    }
    
    @SafeVarargs
    public static <T> Matcher<Iterable<? extends T>> match(BucketMatcher<? super T>... bucketMatchers) {
        LinkedHashMap<BucketMatcher<? super T>, Long> matcherCounts = new LinkedHashMap<>();
        Arrays.asList(bucketMatchers).forEach(matcher -> matcherCounts.put(matcher, 0L));
        return new TypeSafeDiagnosingMatcher<Iterable<? extends T>>() {
            long count = 0;
            @Override
            protected boolean matchesSafely(Iterable<? extends T> item, Description mismatchDescription) {
                // First check, that every item is matched by one bucket.
                for (var it : item) {
                    var matchingMatcher = matcherCounts.keySet().stream().filter(m -> m.getBucketMatcher().matches(it)).findFirst();
                    if (matchingMatcher.isEmpty()) {
                        mismatchDescription.appendText("an item was " + it.toString());
                        return false;
                    } else {
                        count++;
                        matcherCounts.merge(matchingMatcher.get(), 1L, (oldValue, one) -> oldValue + one);
                    }
                }
                
                //Then check, that the bucket probabilities match
                for (var matcher : matcherCounts.keySet()) {
                    var probability = (matcherCounts.get(matcher) * 1.0d) / count;
                    if (!matcher.getPercentileMatcher().matches(probability)) {
                        mismatchDescription.appendText("the probability of bucket (");
                        matcher.getBucketMatcher().describeTo(mismatchDescription);
                        mismatchDescription.appendText(") was " + probability);
                        return false;
                    }
                }
                
                return true;
            }

            @Override
            public void describeTo(Description description) {
                description.appendList("(", "\nor ", ")", matcherCounts.keySet());
            }
        };
    }
    
    

}
