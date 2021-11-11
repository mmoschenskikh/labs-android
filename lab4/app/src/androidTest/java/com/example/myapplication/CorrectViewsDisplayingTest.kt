package com.example.myapplication

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CorrectViewsDisplayingTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun firstIsDisplayingTest() {
        checkIsDisplayed(R.id.fragment1)
        checkDoesNotExist(R.id.fragment2)
        checkDoesNotExist(R.id.fragment3)

        checkDoesNotExist(R.id.bnToFirst)
        checkIsDisplayed(R.id.bnToSecond)
        checkDoesNotExist(R.id.bnToThird)
    }

    @Test
    fun secondIsDisplayingTest() {
        clickOnView(R.id.bnToSecond)

        checkDoesNotExist(R.id.fragment1)
        checkIsDisplayed(R.id.fragment2)
        checkDoesNotExist(R.id.fragment3)

        checkIsDisplayed(R.id.bnToFirst)
        checkDoesNotExist(R.id.bnToSecond)
        checkIsDisplayed(R.id.bnToThird)
    }

    @Test
    fun thirdIsDisplayingTest() {
        clickOnView(R.id.bnToSecond)
        clickOnView(R.id.bnToThird)

        checkDoesNotExist(R.id.fragment1)
        checkDoesNotExist(R.id.fragment2)
        checkIsDisplayed(R.id.fragment3)

        checkIsDisplayed(R.id.bnToFirst)
        checkIsDisplayed(R.id.bnToSecond)
        checkDoesNotExist(R.id.bnToThird)
    }

    @Test
    fun aboutIsDisplayingTest() {
        openAbout()

        checkDoesNotExist(R.id.fragment1)
        checkDoesNotExist(R.id.fragment2)
        checkDoesNotExist(R.id.fragment3)
        checkIsDisplayed(R.id.activity_about)
    }
}