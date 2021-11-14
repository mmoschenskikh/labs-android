package ru.maxultra.lab3p2

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.pressBackUnconditionally
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BackstackDepthTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val scenario: ActivityScenario<MainActivity>
        get() = activityRule.scenario

    @Test
    fun exitFromFirst() {
        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    @Test
    fun exitFromSecond() {
        moveToSecond()

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment1)

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    @Test
    fun exitFromThird() {
        moveToSecond()
        moveToThird()

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment2)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment1)

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    @Test
    fun exitFromFirstAfterThird() {
        moveToSecond()
        moveToThird()
        moveToFirst()

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    @Test
    fun exitFromSecondAfterSomeNavigation() {
        moveToSecond()
        moveToFirst()
        moveToSecond()
        moveToThird()
        moveToSecond()
        moveToThird()
        moveToSecond()

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment1)

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    @Test
    fun exitFromFirstAfterSomeNavigation() {
        moveToSecond()
        moveToFirst()
        moveToSecond()
        moveToThird()
        moveToSecond()
        moveToThird()
        moveToFirst()

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    @Test
    fun exitFromThirdAfterSomeNavigation() {
        moveToSecond()
        moveToFirst()
        moveToSecond()
        moveToThird()
        moveToSecond()
        moveToThird()

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment2)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment1)

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    @Test
    fun aboutFromVariousScreens() {
        moveToSecond()
        moveToFirst()
        moveToSecond()
        moveToThird()
        moveToSecond()
        moveToThird()

        openAbout()
        checkIsDisplayed(R.id.activity_about)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment3)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment2)

        openAbout()
        checkIsDisplayed(R.id.activity_about)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment2)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment1)

        openAbout()
        checkIsDisplayed(R.id.activity_about)

        pressBackUnconditionally()
        checkIsDisplayed(R.id.fragment1)

        pressBackUnconditionally()
        scenario.assertDestroyed()
    }

    private fun moveToFirst() {
        clickOnView(R.id.bnToFirst)
        checkIsDisplayed(R.id.fragment1)
    }

    private fun moveToSecond() {
        clickOnView(R.id.bnToSecond)
        checkIsDisplayed(R.id.fragment2)
    }

    private fun moveToThird() {
        clickOnView(R.id.bnToThird)
        checkIsDisplayed(R.id.fragment3)
    }
}