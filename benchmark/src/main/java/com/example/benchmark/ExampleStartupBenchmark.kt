package com.example.benchmark

import android.os.Build
import android.view.KeyEvent
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.ExperimentalMetricApi
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.PowerMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @RequiresApi(Build.VERSION_CODES.Q)
    @OptIn(ExperimentalMetricApi::class)
    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.faiqaryadewangga.newsapp_coil",
        metrics = listOf(
            FrameTimingMetric(),
            StartupTimingMetric(),
            PowerMetric(type = PowerMetric.Battery())
        ),
        iterations = 1,
        startupMode = StartupMode.COLD,
    ) {

        // Init app
        pressHome()
        startActivityAndWait()

        // Make sure home content is displayed
        device.wait(Until.hasObject(By.desc("Home content")), 3000)
        device.waitForIdle()

        //  Find headline news list
        val headlineNews = device.findObject(By.desc("Headline news"))
        repeat(headlineNews.childCount) {
            headlineNews.scroll(Direction.RIGHT, 100f)
        }
        repeat(headlineNews.childCount) {
            headlineNews.scroll(Direction.LEFT, 100f)
        }

        // Find latest news list
        val latestNews = device.findObject(By.desc("Latest news"))
        repeat(latestNews.childCount) {
            latestNews.scroll(Direction.RIGHT, 100f)
        }
        repeat(latestNews.childCount) {
            latestNews.scroll(Direction.LEFT, 100f)
        }

        // Find home content
        val homeContent = device.findObject(By.desc("Home content"))
        homeContent.setGestureMargin(device.displayWidth / 5)
        homeContent.scroll(Direction.DOWN, 100f)
        homeContent.scroll(Direction.UP, 100f)

        // Find search icon
        val searchIcon = device.findObject(By.desc("Search icon"))
        searchIcon.click()

        // Find search field
        device.wait(Until.hasObject(By.desc("Search field")), 3000)
        val searchField = device.findObject(By.desc("Search field"))
        searchField.click()
        device.pressKeyCode(KeyEvent.KEYCODE_I)
        device.pressKeyCode(KeyEvent.KEYCODE_N)
        device.pressKeyCode(KeyEvent.KEYCODE_D)

        // Find search result
        val searchResult = device.findObject(By.desc("Search result"))
        device.waitForIdle()
        searchResult.children[0].click()

        // Find detail content
        device.wait(Until.hasObject(By.desc("Detail content")), 3000)
        val detailContent = device.findObject(By.desc("Detail content"))
        detailContent.setGestureMargin(device.displayWidth / 5)
        detailContent.scroll(Direction.DOWN, 100f)
        detailContent.scroll(Direction.UP, 100f)
    }
}