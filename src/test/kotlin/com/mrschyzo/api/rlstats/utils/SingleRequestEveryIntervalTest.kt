package com.mrschyzo.api.rlstats.utils

import com.mrschyzo.api.rlstats.utils.commons.ThrottledCallFunnelTimingTest
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class SingleRequestEveryIntervalTest(
        everyMilliseconds: Int,
        requests: Int
)  : ThrottledCallFunnelTimingTest(everyMilliseconds, 1, requests) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters()
        fun data(): Collection<Array<Int>> =
                listOf(
                        arrayOf(50, 2),
                        arrayOf(50, 4),
                        arrayOf(50, 10),
                        arrayOf(150, 3),
                        arrayOf(350, 4)
                )
    }

    @Test
    fun `GIVEN a single thread, WHEN having a 1-sized tunnel, THEN every subsequent call must wait at least that interval`() {
        val callPauses = getExecutionPauses(getSingleThreadedCallTimestamps())

        assert(callPauses.all { it >= everyMilliseconds }) {
            "Every subsequent call must occur in more than ${everyMilliseconds}ms"
        }
    }

    @Test
    fun `GIVEN multiple threads, WHEN having a 1-sized tunnel, THEN every gap between executions must be at least that interval`() {
        val callPauses = getExecutionPauses(getMultiThreadedCallTimestamps())

        assert(callPauses.all { it >= everyMilliseconds }) {
            "Every gap between executions must be at least ${everyMilliseconds}ms"
        }
    }
}