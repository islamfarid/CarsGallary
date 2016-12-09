package com.example.islam.carsviewertask.manufacture.view;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.islam.carsviewertask.Matchers;
import com.example.islam.carsviewertask.R;
import com.example.islam.carsviewertask.common.Constants;
import com.example.islam.carsviewertask.utils.EspressoIdlingResource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by islam on 07/12/16.
 */
@RunWith(AndroidJUnit4.class)
public class CarViewerUITest {
    @Rule
    public ActivityTestRule<ManufacturerActivity> activityRule = new ActivityTestRule<>(
            ManufacturerActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False so we can customize the intent per test method

    @Before
    public void setUp() {
        Espresso.registerIdlingResources(EspressoIdlingResource.getIdlingResource());
        activityRule.launchActivity(new Intent());
    }

    @Test
    public void checkManufacturesRecyclerViewISDisplayed() {

        onView(withId(R.id.manufactures_recyclerview))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkManufacturesPagination_IncreaseNumberOFElemenets() {

        onView(withId(R.id.manufactures_recyclerview))
                .check(ViewAssertions.matches(Matchers.withListSize
                        (Constants.pageSize + Constants.pageSize)));// at first it will be Constants.pageSize twice as onScroll will be called at first time
        onView(withId(R.id.manufactures_recyclerview))
                .perform(RecyclerViewActions.scrollToPosition(Constants.pageSize + (Constants.pageSize / 2)));// after scroll it will load another more 10 elements
        onView(withId(R.id.manufactures_recyclerview))
                .check(ViewAssertions.matches(Matchers.withListSize(Constants.pageSize + Constants.pageSize + Constants.pageSize)));
    }

    @Test
    public void clickManufacture_opensMainTypesListIsDisplayed() {
        onView(withId(R.id.manufactures_recyclerview))
                .perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.main_type_recyclerview)).check(matches(isDisplayed()));
    }

    @Test
    public void opensMainTypes_builtDatesDisplayed() {
        onView(withId(R.id.manufactures_recyclerview))
                .perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.built_dates_recyclerview)).check(matches(isDisplayed()));
    }
}