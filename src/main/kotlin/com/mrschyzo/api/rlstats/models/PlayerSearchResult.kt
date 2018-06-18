package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonProperty

data class PlayerSearchResult(
        @JsonProperty("page")
        val page: Int,

        @JsonProperty("results")
        val results: Int,

        @JsonProperty("totalResults")
        val totalResults: Int,

        @JsonProperty("maxResultsPerPage")
        val maxResultsPerPage: Int,

        @JsonProperty("data")
        val data: List<Player>
)