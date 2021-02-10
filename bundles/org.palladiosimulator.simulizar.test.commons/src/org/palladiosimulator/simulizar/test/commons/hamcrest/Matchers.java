package org.palladiosimulator.simulizar.test.commons.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

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

}
