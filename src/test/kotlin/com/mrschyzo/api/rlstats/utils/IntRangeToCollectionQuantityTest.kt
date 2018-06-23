package com.mrschyzo.api.rlstats.utils

import junit.framework.TestCase
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class IntRangeToCollectionQuantityTest(
        private val inputNumber: Int
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "Generates {0} elements")
        fun data() : Collection<Array<Int>> =
                listOf(
                        arrayOf(0),
                        arrayOf(1),
                        arrayOf(2),
                        arrayOf(3),
                        arrayOf(10)
                )
    }

    @Test
    fun `test if generates exactly n elements`() {
        val generatedElements = inputNumber.create { "a" }.count()

        TestCase.assertEquals(inputNumber, generatedElements)
    }
}