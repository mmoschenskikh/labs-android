package ru.maxultra.lab3p3

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.annotation.IdRes
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId

fun checkIsDisplayed(@IdRes viewId: Int) {
    onView(withId(viewId)).check(matches(isDisplayed()))
}

fun checkDoesNotExist(@IdRes viewId: Int) {
    onView(withId(viewId)).check(doesNotExist())
}

fun clickOnView(@IdRes viewId: Int) {
    onView(withId(viewId)).perform(click())
}

fun <T : Activity> ActivityScenario<T>.assertDestroyed() {
    assert(state == Lifecycle.State.DESTROYED)
}

fun <T : Activity> ActivityScenario<T>.rotateAndWait(millis: Long = 1000L) {
    onActivity { activity ->
        activity.requestedOrientation =
            if (activity.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            else
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
    Thread.sleep(millis)
}