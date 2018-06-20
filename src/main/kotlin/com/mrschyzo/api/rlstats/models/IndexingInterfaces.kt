package com.mrschyzo.api.rlstats.models

interface WithId<out T> {
    val id: T
}

interface WithName {
    val name: String
}

interface WithInfoPair<out T> : WithId<T>, WithName