package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PlayerEntry(
        @JsonProperty("platformId")
        val platformId: Int,

        @JsonProperty("uniqueId")
        val uniqueId: String
)