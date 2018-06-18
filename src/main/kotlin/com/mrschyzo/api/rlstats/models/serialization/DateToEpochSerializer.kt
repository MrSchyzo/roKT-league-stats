package com.mrschyzo.api.rlstats.models.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.util.*

class DateToEpochSerializer : JsonSerializer<Date>() {
    override fun serialize(value: Date?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        if (value != null)
            gen?.writeObject(value.time / 1000)
    }
}