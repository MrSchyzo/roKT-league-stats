package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Playlist(
        @JsonProperty("id")
        override val id: Int,

        @JsonProperty("platformId")
        val platformId: Int,

        @JsonProperty("name")
        override val name: String,

        @JsonProperty("population")
        val population: Population
) : WithInfoPair<Int>

fun Playlist.isRanked() = name.startsWith("Ranked")