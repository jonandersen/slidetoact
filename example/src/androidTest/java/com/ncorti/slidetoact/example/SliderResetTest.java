package com.ncorti.slidetoact.example;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ncorti.slidetoact.SlideToActView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class SliderResetTest {

    @Rule
    public ActivityTestRule<SampleActivity> mActivityRule =
            new ActivityTestRule<SampleActivity>(SampleActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, MainActivity.class);
                    result.putExtra(SampleActivity.EXTRA_PRESSED_BUTTON, R.id.button_area_margin);
                    return result;
                }
            };

    @Test
    public void testSlideToActView_withSwipeRight_isCompleted() throws InterruptedException {
        onView(withId(R.id.slide_1)).perform(swipeRight());
        Thread.sleep(1200);
        assertTrue(((SlideToActView) mActivityRule.getActivity().findViewById(R.id.slide_1)).isCompleted());
    }

    @Test
    public void testSlideToActView_withSwipeAndReset_isNotCompleted() throws InterruptedException {
        onView(withId(R.id.slide_1)).perform(swipeRight());
        Thread.sleep(1200);
        onView(withId(R.id.reset)).perform(click());
        Thread.sleep(700);
        assertFalse(((SlideToActView) mActivityRule.getActivity().findViewById(R.id.slide_1)).isCompleted());
    }
}