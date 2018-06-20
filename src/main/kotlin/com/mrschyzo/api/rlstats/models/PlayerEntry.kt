package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PlayerEntry(
        @JsonProperty("platformId")
        override val id: Int,

        @JsonProperty("uniqueId")
        override val name: String
) : WithInfoPair<Int>