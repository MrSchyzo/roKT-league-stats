package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.mrschyzo.api.rlstats.models.serialization.DateToEpochSerializer
import com.mrschyzo.api.rlstats.models.serialization.EpochToDateSerializer
import java.util.*

data class Population(
        @JsonProperty("players")
        val players: Long,

        @JsonProperty("updatedAt")
        @JsonSerialize(using = DateToEpochSerializer::class, `as` = Long::class)
        @JsonDeserialize(using = EpochToDateSerializer::class, `as` = Date::class)
        val updatedAt: Date
)