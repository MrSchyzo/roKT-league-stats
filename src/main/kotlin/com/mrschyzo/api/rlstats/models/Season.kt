package com.mrschyzo.api.rlstats.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.mrschyzo.api.rlstats.models.serialization.DateToEpochSerializer
import com.mrschyzo.api.rlstats.models.serialization.EpochToDateSerializer
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Season(
        @JsonProperty("seasonId")
        val seasonId: Int,

        @JsonProperty("startedOn")
        @JsonSerialize(using = DateToEpochSerializer::class, `as`=Long::class)
        @JsonDeserialize(using = EpochToDateSerializer::class, `as`= Date::class)
        val startedOn: Date,

        @JsonProperty("endedOn")
        @JsonSerialize(using = DateToEpochSerializer::class, `as`=Long::class)
        @JsonDeserialize(using = EpochToDateSerializer::class, `as`= Date::class)
        val endedOn: Date?
)