package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Tier(
        @JsonProperty("tierId")
        override val id: Int,

        @JsonProperty("tierName")
        override val name: String
) : WithInfoPair<Int>