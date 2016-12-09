package com.example.islam.carsviewertask;

import android.graphics.drawable.ColorDrawable;
import android.support.test.espresso.intent.Checks;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by islam on 08/12/16.
 */

public class Matchers {
    public static Matcher<View> withListSize(final int size) {
        return new TypeSafeMatcher<View>() {
            int currentNumber;

            @Override
            public boolean matchesSafely(final View view) {
                currentNumber = ((RecyclerView) view).getAdapter().getItemCount();
                return ((RecyclerView) view).getAdapter().getItemCount() == size;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("RecyclerView should have " + currentNumber + " not equal " + size + " items");
            }
        };
    }

    public static Matcher<View> withBgColor(final int color) {
        Checks.checkNotNull(color);
        return new BoundedMatcher<View, LinearLayout>(LinearLayout.class) {
            @Override
            public boolean matchesSafely(LinearLayout row) {
                return color == ((ColorDrawable) row.getBackground()).getColor();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text color: ");
            }
        };
    }

}
