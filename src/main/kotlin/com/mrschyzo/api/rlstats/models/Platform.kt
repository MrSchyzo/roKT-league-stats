package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Platform(
        @JsonProperty("id")
        override val id: Int,

        @JsonProperty("name")
        override val name: String
) : WithInfoPair<Int>