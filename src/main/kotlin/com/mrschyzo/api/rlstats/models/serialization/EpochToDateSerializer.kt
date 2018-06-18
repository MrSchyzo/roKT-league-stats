package com.mrschyzo.api.rlstats.models.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.util.*

class EpochToDateSerializer : JsonDeserializer<Date?>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Date? {
        val currentToken = p.currentToken

        if (currentToken == JsonToken.VALUE_NULL)
            return null

        if (currentToken != JsonToken.VALUE_NUMBER_INT)
            throw ctxt.mappingException(Date::class.java)

        return Date(p.valueAsLong * 1000)
    }
}