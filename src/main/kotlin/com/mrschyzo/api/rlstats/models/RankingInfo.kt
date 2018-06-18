package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonProperty

data class RankingInfo(
        @JsonProperty("rankPoints")
        val rankPoints: Int,

        @JsonProperty("matchesPlayed")
        val matchesPlayed: Int,

        @JsonProperty("tier")
        val tier: Int,

        @JsonProperty("division")
        val division: Int
)