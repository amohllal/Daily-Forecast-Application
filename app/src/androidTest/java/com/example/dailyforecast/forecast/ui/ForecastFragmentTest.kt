package com.example.dailyforecast.forecast.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.dailyforecast.R
import org.junit.Rule
import org.junit.Test

class ForecastFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForecastActivity::class.java)

    @Test
    fun testSpinnerShown() {
        onView(withId(R.id.spinner))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testProgressShown() {
        onView(withId(R.id.progressBar))
            .check(matches(isDisplayed()))
    }
}