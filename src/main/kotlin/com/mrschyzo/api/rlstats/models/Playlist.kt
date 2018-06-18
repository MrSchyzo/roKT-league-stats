package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Playlist(
        @JsonProperty("id")
        val id: Int,

        @JsonProperty("platformId")
        val platformId: Int,

        @JsonProperty("name")
        val name: String,

        @JsonProperty("population")
        val population: Population
)