package com.raywenderlich.android.spacingout.images

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.raywenderlich.android.spacingout.CoroutinesTestRule
import com.raywenderlich.android.spacingout.SpacingAnalytics
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ImagesViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `An analytics call is made when images are loaded`() {

        val imagesListProvider = mockk<ImageListProvider>()
        val spacingAnalytics = spyk<SpacingAnalytics>()

        val imagesViewModel = ImagesViewModel(imagesListProvider, spacingAnalytics)

        verify(exactly = 1) { spacingAnalytics.logEvent("Fetching images") }


    }

}