package org.palladiosimulator.simulizar.test.commons.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.SelfDescribing;

public class BucketMatcher<T> implements SelfDescribing {
    private final Matcher<Double> percentileMatcher;
    private final Matcher<? super T> bucketMatcher;

    public BucketMatcher(Matcher<Double> percentileMatcher, Matcher<? super T> bucketMatcher) {
        this.percentileMatcher = percentileMatcher;
        this.bucketMatcher = bucketMatcher;
        
    }

    public Matcher<Double> getPercentileMatcher() {
        return percentileMatcher;
    }

    public Matcher<? super T> getBucketMatcher() {
        return bucketMatcher;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("(");
        getBucketMatcher().describeTo(description);
        description.appendText(") with a probability of (");
        getPercentileMatcher().describeTo(description);
        description.appendText(")");
    }


}
