package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.mrschyzo.api.rlstats.models.serialization.DateToEpochSerializer
import com.mrschyzo.api.rlstats.models.serialization.EpochToDateSerializer
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Player(
        @JsonProperty("uniqueId")
        val uniqueId: String,

        @JsonProperty("displayName")
        val displayName: String,

        @JsonProperty("platform")
        val platform: Platform,

        @JsonProperty("avatar")
        val avatar: String?,

        @JsonProperty("profileUrl")
        val profileUrl: String?,

        @JsonProperty("signatureUrl")
        val signatureUrl: String?,

        @JsonProperty("stats")
        val stats: Map<Stat, Int>,

        @JsonProperty("lastRequested")
        @JsonSerialize(using = DateToEpochSerializer::class, `as` = Long::class)
        @JsonDeserialize(using = EpochToDateSerializer::class, `as` = Date::class)
        val lastRequested: Date,

        @JsonProperty("createdAt")
        @JsonSerialize(using = DateToEpochSerializer::class, `as` = Long::class)
        @JsonDeserialize(using = EpochToDateSerializer::class, `as` = Date::class)
        val createdAt: Date,

        @JsonProperty("updatedAt")
        @JsonSerialize(using = DateToEpochSerializer::class, `as` = Long::class)
        @JsonDeserialize(using = EpochToDateSerializer::class, `as` = Date::class)
        val updatedAt: Date,

        @JsonProperty("nextUpdateAt")
        @JsonSerialize(using = DateToEpochSerializer::class, `as` = Long::class)
        @JsonDeserialize(using = EpochToDateSerializer::class, `as` = Date::class)
        val nextUpdateAt: Date,

        @JsonProperty("rankedSeasons")
        val rankedSeasons: Map<Int, Map<Int, RankingInfo>>
)