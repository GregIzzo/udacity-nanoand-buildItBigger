package com.example.android.BuildItBigger;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class TestJokeButtonAsyncJokeString {
    //tell joke button id: button_telljoke
    //@Rule
    //    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private String JOKEMATCHSTRING = "This is a funny Joke!";
    @Rule
    public ActivityTestRule<MainActivity>  mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickJokeButton_checkAsyncString(){
        //Click Joke Button
        onView(withId(R.id.button_telljoke))
                .perform(click());
        //Wait for my AppLibraryActivity to launch and check the string it shows.
        // this activity is launched after the async call is done, with the result of that call
        // being passed to the activity
        onView(withId(R.id.tv_joke_text))
                .check(matches(withText(JOKEMATCHSTRING)));


    }

}
