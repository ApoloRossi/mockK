package com.raywenderlich.android.spacingout.images

import com.raywenderlich.android.spacingout.models.ApodImage
import com.raywenderlich.android.spacingout.network.SpacingOutApi
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test
import retrofit2.mock.Calls

class ImageListProviderTest {

    @Test
    fun `a list of 30 images is returned`() = runBlocking {

        val api = mockk<SpacingOutApi>()


        coEvery {
            api.getImage(any())
        } answers {
            ApodImage(
                "08-11-2019",
                "Teste",
                "www.testurl.com",
                "test",
                "www.testurl.com",
                "video"
            )
        }


        val provider = ImageListProvider(api)

        val images = provider.buildImageList()

        assertEquals(30, images.size)
    }
}