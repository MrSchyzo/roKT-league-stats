package com.mrschyzo.api.rlstats.utils

/**
 * Interface CallFunnel
 *
 * ---
 *
 * This interface is used to wrap an execution block into any control mechanism.
 * These instances can be seen as a funnel for some execution block.
 */
interface CallFunnel {
    /**
     * Runs a block of code inside this funnel
     */
    fun <T> run(block: () -> T) : T

    companion object {
        /**
         * Returns a neutral funnel, it just runs the execution blocks without any modification of the flow
         */
        val neutralFunnel : CallFunnel = FreeCallFunnel()
    }

    private class FreeCallFunnel : CallFunnel {
        override fun <T> run(block: () -> T): T = block()
    }
}