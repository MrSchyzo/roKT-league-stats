package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Platform(
        @JsonProperty("id")
        val id: Int,
        @JsonProperty("name")
        val name: String
)