package com.severett.common.model

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.severett.common.serde.jackson.FooDeserializer
import com.severett.common.serde.jackson.FooSerializer
import com.severett.common.serde.jsonb.FooDeserializer as JsonbFooDeserializer
import com.severett.common.serde.jsonb.FooSerializer as JsonbFooSerializer
import jakarta.json.bind.annotation.JsonbTypeDeserializer
import jakarta.json.bind.annotation.JsonbTypeSerializer
import kotlinx.serialization.Serializable

@Serializable
// Custom Jackson serde declarations
@JsonSerialize(using = FooSerializer::class)
@JsonDeserialize(using = FooDeserializer::class)
// Custom JSON-B serde declarations
@JsonbTypeSerializer(JsonbFooSerializer::class)
@JsonbTypeDeserializer(JsonbFooDeserializer::class)
data class Foo(val fizz: String, val bazz: List<String>, val count: UInt) {
    companion object {
        const val FIZZ_FIELD = "fizz"
        const val BAZZ_FIELD = "bazz"
        const val COUNT_FIELD = "count"
    }
}
