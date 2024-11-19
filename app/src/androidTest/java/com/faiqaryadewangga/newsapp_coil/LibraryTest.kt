package com.faiqaryadewangga.newsapp_coil

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.faiqaryadewangga.newsapp_coil.navigation.AppNavigation
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LibraryTest {

    @get:Rule(order = 0)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeRule.activity.setContent {
            AppNavigation()
        }
    }

    @Test
    fun runLibraryTest() {
        composeRule
            .onNodeWithTag("Home content")
            .assertIsDisplayed()

        composeRule
            .onNodeWithTag("Home content")
            .performScrollTo()

        composeRule
            .onNodeWithTag("Headline news")
            .performScrollTo()

        composeRule
            .onNodeWithTag("Latest news")
            .performScrollTo()

        composeRule
            .onNodeWithTag("Search icon")
            .assertIsDisplayed()
    }
}