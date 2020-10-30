package com.android.bjapplication

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.bjapplication.network.Constants
import com.android.bjapplication.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)

    @get:Rule
    val activityRule = ActivityScenarioRule<MainActivity>(intent)

    @Test
    fun tab_Present() {
        onView(withText(R.string.sources)).check(matches(isDisplayed()))
        onView(withText(R.string.all_news)).check(matches(isDisplayed()))
    }

    @Test
    fun api_ErrorState() {
        Thread.sleep(5000)
        onView(withId(R.id.errorInfo)).check(matches(hasDescendant(withText(Constants.SERVER_ERROR))))
    }

    @Test
    fun internetConnection_ErrorState() {
        Thread.sleep(1000)
        onView(withId(R.id.errorInfo)).check(matches(hasDescendant(withText(Constants.INTERNET_ERROR))))
    }


}