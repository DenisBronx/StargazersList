package com.denisbrandi.stargazers.stargazerslist.view;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.denisbrandi.stargazers.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class StargazersListActivityTest {

    @Rule
    public ActivityTestRule<StargazersListActivity> mActivityTestRule = new ActivityTestRule<>(StargazersListActivity.class);

    @Test
    public void stargazersListActivityTest() {

        ViewInteraction owner = onView(withId(R.id.owner));
        owner.perform(replaceText("ReactiveX"), closeSoftKeyboard());

        ViewInteraction repository = onView(withId(R.id.repository));
        repository.perform(replaceText("RxAndroid"), closeSoftKeyboard());

        ViewInteraction floatingActionButton = onView(withId(R.id.fab_search));
        floatingActionButton.perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition(29));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition(59));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition(89));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
