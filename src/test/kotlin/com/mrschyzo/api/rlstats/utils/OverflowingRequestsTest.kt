package com.mrschyzo.api.rlstats.utils

import com.mrschyzo.api.rlstats.utils.commons.ThrottledCallFunnelTimingTest
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class OverflowingRequestsTest(
        everyMilliseconds: Int,
        maxRequestsPerPeriod: Int,
        requests: Int
) : ThrottledCallFunnelTimingTest(everyMilliseconds, maxRequestsPerPeriod, requests) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters()
        fun data(): Collection<Array<Int>> =
                listOf(
                        arrayOf(200, 3, 5),
                        arrayOf(200, 5, 6),
                        arrayOf(100, 2, 5),
                        arrayOf(100, 9, 11),
                        arrayOf(150, 3, 10)
                )
    }

    @Test
    fun `GIVEN a single thread, WHEN having a more requests than funnel size, THEN call rate must be mitigated`() {
        val callSpan = getExecutionSpan(getSingleThreadedCallTimestamps())

        assert(callSpan >= expectedMinimumExecutionSpan) {
            "Whole execution set must not fit inside a $everyMilliseconds*${requests/maxRequestsPerPeriod}ms window"
        }
    }

    @Test
    fun `GIVEN multiple threads, WHEN having a more requests than funnel size, THEN call rate must be mitigated`() {
        val callSpan = getExecutionSpan(getMultiThreadedCallTimestamps())

        assert(callSpan >= expectedMinimumExecutionSpan) {
            "Whole execution set must not fit inside a $everyMilliseconds*${requests/maxRequestsPerPeriod}ms window"
        }
    }
}