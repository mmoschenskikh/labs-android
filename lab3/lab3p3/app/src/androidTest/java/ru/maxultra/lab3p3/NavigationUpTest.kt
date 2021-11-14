package ru.maxultra.lab3p3

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationUpTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun firstDoesNotHaveUpNavigation() {
        onView(withContentDescription(R.string.abc_action_bar_up_description)).check(doesNotExist())
    }

    @Test
    fun screensExceptFirstHaveUpNavigation() {
        clickOnView(R.id.bnToSecond)
        checkIsDisplayed(R.id.fragment2)
        checkIsUpNavigationDisplayed()

        clickOnView(R.id.bnToThird)
        checkIsDisplayed(R.id.fragment3)
        checkIsUpNavigationDisplayed()

        openAbout()
        checkIsDisplayed(R.id.activity_about)
        checkIsUpNavigationDisplayed()
    }

    @Test
    fun checkUpNavigationBehavior() {
        clickOnView(R.id.bnToSecond)
        checkIsDisplayed(R.id.fragment2)

        clickOnNavigateUpButton()
        checkIsDisplayed(R.id.fragment1)

        clickOnView(R.id.bnToSecond)
        checkIsDisplayed(R.id.fragment2)

        clickOnView(R.id.bnToThird)
        checkIsDisplayed(R.id.fragment3)

        clickOnNavigateUpButton()
        checkIsDisplayed(R.id.fragment2)

        openAbout()
        checkIsDisplayed(R.id.activity_about)

        clickOnNavigateUpButton()
        checkIsDisplayed(R.id.fragment2)

        clickOnNavigateUpButton()
        checkIsDisplayed(R.id.fragment1)
    }

    private fun checkIsUpNavigationDisplayed() {
        onView(withContentDescription(R.string.abc_action_bar_up_description))
            .check(matches(isDisplayed()))
    }

    private fun clickOnNavigateUpButton() {
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(ViewActions.click())
    }
}