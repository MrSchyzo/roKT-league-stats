package com.mrschyzo.api.rlstats.utils

inline fun <T> Int.create(creator: () -> T) : Collection<T> = (1..this).map{ creator() }

inline fun <T> Long.create(creator: () -> T) : Collection<T> = (1..this).map{ creator() }

fun <E, Out> Collection<E>.stepMap(accumulator: (previous: E, next: E) -> Out) : Collection<Out> {
    var current = this.first()

    return this.drop(1).map {
        val computation = accumulator(current, it)
        current = it
        computation
    }
}