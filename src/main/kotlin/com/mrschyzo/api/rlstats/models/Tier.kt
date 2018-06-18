package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Tier(
        @JsonProperty("tierId")
        val tierId: Int,

        @JsonProperty("tierName")
        val tierName: String
)