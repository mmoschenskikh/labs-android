package com.example.myapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.pressBackUnconditionally
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConfigurationChangeTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val scenario: ActivityScenario<MainActivity>
        get() = activityRule.scenario

    @Test
    fun firstDoesNotLoseStateTest() {
        checkIsDisplayed(R.id.fragment1)
        scenario.rotateAndWait()
        checkIsDisplayed(R.id.fragment1)

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    @Test
    fun secondDoesNotLoseStateTest() {
        moveToSecondAndRotate()

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment1)
        scenario.rotateAndWait()
        checkIsDisplayed(R.id.fragment1)

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    @Test
    fun thirdDoesNotLoseStateTest() {
        moveToSecondAndRotate()
        moveToThirdAndRotate()

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment2)
        scenario.rotateAndWait()
        checkIsDisplayed(R.id.fragment2)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment1)
        scenario.rotateAndWait()
        checkIsDisplayed(R.id.fragment1)

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    @Test
    fun firstDoesNotLoseStateAfterSomeNavigation() {
        moveToSecondAndRotate()
        moveToFirstAndRotate()
        moveToSecondAndRotate()
        moveToThirdAndRotate()
        moveToSecondAndRotate()
        moveToThirdAndRotate()
        moveToFirstAndRotate()

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    @Test
    fun secondDoesNotLoseStateAfterSomeNavigation() {
        moveToSecondAndRotate()
        moveToFirstAndRotate()
        moveToSecondAndRotate()
        moveToThirdAndRotate()
        moveToSecondAndRotate()
        moveToThirdAndRotate()
        moveToSecondAndRotate()

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment1)
        scenario.rotateAndWait()
        checkIsDisplayed(R.id.fragment1)

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    @Test
    fun thirdDoesNotLoseStateAfterSomeNavigation() {
        moveToSecondAndRotate()
        moveToFirstAndRotate()
        moveToSecondAndRotate()
        moveToThirdAndRotate()
        moveToSecondAndRotate()
        moveToThirdAndRotate()

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment2)
        scenario.rotateAndWait()
        checkIsDisplayed(R.id.fragment2)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment1)
        scenario.rotateAndWait()
        checkIsDisplayed(R.id.fragment1)

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    @Test
    fun aboutDoesNotLostState() {
        moveToSecondAndRotate()
        moveToFirstAndRotate()
        moveToSecondAndRotate()
        moveToThirdAndRotate()
        moveToSecondAndRotate()
        moveToThirdAndRotate()

        openAbout()
        checkIsDisplayed(R.id.activity_about)
        scenario.rotateAndWait()
        checkIsDisplayed(R.id.activity_about)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment3)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment2)

        openAbout()
        checkIsDisplayed(R.id.activity_about)
        scenario.rotateAndWait()
        checkIsDisplayed(R.id.activity_about)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment2)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment1)

        openAbout()
        checkIsDisplayed(R.id.activity_about)
        scenario.rotateAndWait()
        checkIsDisplayed(R.id.activity_about)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment1)

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    private fun moveToFirstAndRotate() {
        clickOnView(R.id.bnToFirst)
        checkIsDisplayed(R.id.fragment1)

        scenario.rotateAndWait()
        checkIsDisplayed(R.id.fragment1)
    }

    private fun moveToSecondAndRotate() {
        clickOnView(R.id.bnToSecond)
        checkIsDisplayed(R.id.fragment2)

        scenario.rotateAndWait()
        checkIsDisplayed(R.id.fragment2)
    }

    private fun moveToThirdAndRotate() {
        clickOnView(R.id.bnToThird)
        checkIsDisplayed(R.id.fragment3)

        scenario.rotateAndWait()
        checkIsDisplayed(R.id.fragment3)
    }
}