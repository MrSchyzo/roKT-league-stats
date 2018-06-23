package com.mrschyzo.api.rlstats.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class StepMapTest(
        private val input: Collection<Int>,
        private val output: Collection<Int>
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters()
        fun data() : Collection<Array<List<Int>>> =
            listOf(
                arrayOf(
                        listOf(1, 2, 3),
                        listOf(1, 1)
                ),
                arrayOf(
                        listOf(0, 20, 10, 15, 18),
                        listOf(20, -10, 5, 3)
                ),
                arrayOf(
                        listOf(1, 0),
                        listOf(-1)
                ),
                arrayOf(
                        listOf(10),
                        listOf()
                )
            )
    }

    @Test
    fun `test expected outcome`() {
        val differences = input.stepMap { previous, next -> next - previous }

        assertEquals(differences, output)
    }
}