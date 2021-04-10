package com.raywenderlich.android.spacingout

import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Test

class SpacingAnalyticsTest() {


    @Test
    fun `when log event check parameters are correct`() {

        mockkObject(ThirdPartyAnalyticsProvider)

        val slot = slot<Map<String, String>>()

        every {
            ThirdPartyAnalyticsProvider.logEvent(any(),
                capture(slot))
        } just runs


        SpacingAnalytics().logEvent(
            "click",
            mapOf(
                "attribute" to "value"
            )
        )

        assertEquals(slot.captured,  mapOf(
            "attribute" to "value",
            "client_type" to "Android",
            "version" to "1"
        ))


    }




}