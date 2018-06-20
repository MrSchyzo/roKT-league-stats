package com.mrschyzo.api.rlstats.utils

fun <T> Int.generate(generator: () -> T) : Collection<T> = (1..this).map{ generator() }
fun <T> Long.generate(generator: () -> T) : Collection<T> = (1..this).map{ generator() }