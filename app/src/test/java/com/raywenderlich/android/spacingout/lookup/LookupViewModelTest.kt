package com.raywenderlich.android.spacingout.lookup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.raywenderlich.android.spacingout.CoroutinesTestRule
import com.raywenderlich.android.spacingout.logEvent
import com.raywenderlich.android.spacingout.models.ApodImage
import com.raywenderlich.android.spacingout.models.EarthImage
import com.raywenderlich.android.spacingout.network.SpacingOutApi
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.*

class LookupViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTask = InstantTaskExecutorRule()


    @Test
    fun `fetch image from api return success should notify`() {
        val spacingApi = mockk<SpacingOutApi>()
        mockkObject(SpacingOutApi)

        every {
            SpacingOutApi.create()
        } returns spacingApi


        coEvery { spacingApi.getEarthImagery(anyFloat(), anyFloat()) } returns EarthImage("https://image.com.br")

        val lookupViewModel = LookupViewModel()

        lookupViewModel.latLongInput(anyFloat(), anyFloat())

        assertEquals("https://image.com.br", lookupViewModel.imageLiveData.value)
    }


    @Test
    fun `An event is logged whenever coordinates are input`() {
        val mockApi = mockk<SpacingOutApi>()

        mockkObject(SpacingOutApi)
        mockkStatic("com.raywenderlich.android.spacingout.UtilsKt")

        every { SpacingOutApi.create() } returns mockApi

        coEvery { mockApi.getEarthImagery(any(), any()) } returns EarthImage("testurl")

        val viewModel = LookupViewModel()
        viewModel.latLongInput(10f, 10f)

        verify(exactly = 1) { viewModel.logEvent("image retrieved", mapOf("latitude" to "10.0", "longitude" to "10.0"))}
    }


}