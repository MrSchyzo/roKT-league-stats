package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PlayerStats(
        @JsonProperty("wins")
        val wins: Int,

        @JsonProperty("goals")
        val goals: Int,

        @JsonProperty("mvps")
        val MVPs: Int,

        @JsonProperty("saves")
        val saves: Int,

        @JsonProperty("shots")
        val shots: Int,

        @JsonProperty("assists")
        val assists: Int
)

fun PlayerStats.getAccuracy() = if (shots > 0) goals.toFloat() / shots else .0f

fun PlayerStats.getValuability() = if (wins > 0) goals.toFloat() / wins else .0f