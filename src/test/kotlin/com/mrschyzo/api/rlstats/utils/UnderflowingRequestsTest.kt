package com.mrschyzo.api.rlstats.utils

import com.mrschyzo.api.rlstats.utils.commons.ThrottledCallFunnelTimingTest
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class UnderflowingRequestsTest(
        everyMilliseconds: Int,
        maxRequestsPerPeriod: Int,
        requests: Int
) : ThrottledCallFunnelTimingTest(everyMilliseconds, maxRequestsPerPeriod, requests) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters()
        fun data(): Collection<Array<Int>> =
                listOf(
                        arrayOf(4000, 5, 3),
                        arrayOf(4000, 6, 5),
                        arrayOf(4000, 2, 2),
                        arrayOf(4000, 10, 9),
                        arrayOf(4000, 11, 11)
                )
    }

    @Test
    fun `GIVEN a single thread, WHEN having a big enough funnel, THEN every subsequent call shouldn't wait the interval`() {
        val callTimestamps = getSingleThreadedCallTimestamps()
        val callPauses = getExecutionPauses(callTimestamps)
        val callSpan = getExecutionSpan(callTimestamps)

        assert(callSpan < everyMilliseconds) {
            "All executions must span in a ${everyMilliseconds}ms window"
        }
        assert(callPauses.all { it <= everyMilliseconds }) {
            "Every subsequent call should wait less than ${everyMilliseconds}ms"
        }
    }

    @Test
    fun `GIVEN multiple threads, WHEN having a big enough funnel, THEN every gap between executions must be less than that interval`() {
        val callTimestamps = getMultiThreadedCallTimestamps()
        val callPauses = getExecutionPauses(callTimestamps)
        val callSpan = getExecutionSpan(callTimestamps)

        assert(callSpan < everyMilliseconds) {
            "All executions must span in a ${everyMilliseconds}ms window"
        }
        assert(callPauses.all { it <= everyMilliseconds }) {
            "Every gap between executions must be less than ${everyMilliseconds}ms"
        }
    }
}