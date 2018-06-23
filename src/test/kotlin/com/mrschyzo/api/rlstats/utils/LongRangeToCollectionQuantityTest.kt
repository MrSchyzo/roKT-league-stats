package com.mrschyzo.api.rlstats.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class LongRangeToCollectionQuantityTest(
        private val inputNumber: Long
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "Generates {0} elements")
        fun data() : Collection<Array<Long>> =
                listOf(
                        arrayOf(0L),
                        arrayOf(1L),
                        arrayOf(2L),
                        arrayOf(3L),
                        arrayOf(10L)
                )
    }

    @Test
    fun `test if generates exactly n elements`() {
        val generatedElements = inputNumber.create { "a" }.count()

        assertEquals(inputNumber.toInt(), generatedElements)
    }
}