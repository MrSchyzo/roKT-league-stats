package com.mrschyzo.api.rlstats.utils

import com.mrschyzo.api.rlstats.utils.data.TimeSpan
import java.util.concurrent.DelayQueue
import java.util.concurrent.Delayed
import java.util.concurrent.TimeUnit

class ThrottledCallFunnel(private val interval: TimeSpan, calls: Long = 1) : CallFunnel {
    private val timestampQueue: DelayQueue<ThrottleToken> = DelayQueue(calls.generate { ThrottleToken(0, interval.unit) })

    override fun <T> run(block: () -> T): T {
        try {
            timestampQueue.take()
            return block()
        } finally {
            timestampQueue.add(ThrottleToken(interval.span, interval.unit))
        }
    }

    class ThrottleToken(delay: Long, storedUnit: TimeUnit = TimeUnit.MILLISECONDS) : Delayed {
        private var expiresAt: Long = 0
        private val nanoUnit = TimeUnit.NANOSECONDS

        init {
            expiresAt = now() + nanoUnit.convert(delay, storedUnit)
        }

        private fun now() = System.nanoTime()

        override fun compareTo(other: Delayed): Int =
                getDelay(nanoUnit).compareTo(other.getDelay(nanoUnit))

        override fun getDelay(unit: TimeUnit): Long = unit.convert(expiresAt - now(), nanoUnit)
    }
}