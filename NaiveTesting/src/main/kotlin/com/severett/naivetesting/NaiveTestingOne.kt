package com.severett.naivetesting

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.severett.common.model.Foo
import jakarta.json.bind.JsonbBuilder
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() {
    val foo = Foo("FUZZ", listOf("BIZZ", "BUZZ"), 20u)
    // Kotlin
    val kotlinStart = System.currentTimeMillis()
    Json.decodeFromString<Foo>(Json.encodeToString(foo))
    println("KOTLIN: ${System.currentTimeMillis() - kotlinStart}ms")
    // GSON
    val gson = Gson()
    val gsonStart = System.currentTimeMillis()
    gson.fromJson(gson.toJson(foo), Foo::class.java)
    println("GSON: ${System.currentTimeMillis() - gsonStart}ms")
    // Jackson
    val objectMapper = ObjectMapper()
    val jacksonStart = System.currentTimeMillis()
    objectMapper.readValue(objectMapper.writeValueAsString(foo), Foo::class.java)
    println("JACKSON: ${System.currentTimeMillis() - jacksonStart}ms")
    // JSON-B
    val jsonb = JsonbBuilder.create()
    val jsonbStart = System.currentTimeMillis()
    jsonb.fromJson(jsonb.toJson(foo), Foo::class.java)
    println("JSON-B DECODED: ${System.currentTimeMillis() - jsonbStart}ms")
}
