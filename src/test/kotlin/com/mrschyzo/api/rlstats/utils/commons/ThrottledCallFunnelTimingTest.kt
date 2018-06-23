package com.mrschyzo.api.rlstats.utils.commons

import com.mrschyzo.api.rlstats.utils.ThrottledCallFunnel
import com.mrschyzo.api.rlstats.utils.create
import com.mrschyzo.api.rlstats.utils.data.TimeSpan
import com.mrschyzo.api.rlstats.utils.stepMap
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.concurrent.*

open class ThrottledCallFunnelTimingTest(
        protected val everyMilliseconds: Int,
        protected val maxRequestsPerPeriod: Int,
        protected val requests: Int
) {
    protected val expectedMinimumExecutionSpan
        get() = everyMilliseconds * (requests/maxRequestsPerPeriod)

    private lateinit var funnel: ThrottledCallFunnel

    @Before
    fun setUp() {
        funnel = ThrottledCallFunnel(
                TimeSpan(everyMilliseconds.toLong(), TimeUnit.MILLISECONDS),
                maxRequestsPerPeriod.toLong()
        )
    }

    protected fun getSingleThreadedCallTimestamps() : Collection<Long> =
            requests.create { getFunnelTimestamp() }

    protected fun getMultiThreadedCallTimestamps() : Collection<Long> {
        val executor = Executors.newCachedThreadPool()
        val service = ExecutorCompletionService<Long>(executor)

        val submissions = requests.create { service.submit { getFunnelTimestamp() } }
        return submissions.map { it.get() }.sortedBy { it }
    }

    private fun getFunnelTimestamp() = funnel.run { timestamp() }

    private fun timestamp() = System.currentTimeMillis()

    protected fun getExecutionPauses(callTimestamps: Collection<Long>) =
            callTimestamps.stepMap { previous, next -> next - previous }

    protected fun getExecutionSpan(callTimestamps: Collection<Long>) : Long =
            if (callTimestamps.count() == 0)
                0
            else
                callTimestamps.max()!! - callTimestamps.min()!!
}