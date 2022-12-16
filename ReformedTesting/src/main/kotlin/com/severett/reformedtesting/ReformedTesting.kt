package com.severett.reformedtesting

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.severett.common.model.Foo
import jakarta.json.bind.JsonbBuilder
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import java.util.concurrent.*

@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
open class ReformedTesting {
    private val foo = Foo("FUZZ", listOf("BIZZ", "BUZZ"), 20u)
    private val fooStr = "{\"fizz\":\"FUZZ\",\"bazz\":[\"BIZZ\",\"BUZZ\"],\"count\":20}"
    private val gson = Gson()
    private val objectMapper = ObjectMapper()
    private val jsonb = JsonbBuilder.create()

    @Benchmark
    fun kotlinSerialize() = Json.encodeToString(foo)

    @Benchmark
    fun kotlinDeserialize() = Json.decodeFromString<Foo>(fooStr)

    @Benchmark
    fun gsonSerialize(): String = gson.toJson(foo)

    @Benchmark
    fun gsonDeserialize(): Foo = gson.fromJson(fooStr, Foo::class.java)

    @Benchmark
    fun jacksonSerialize(): String = objectMapper.writeValueAsString(foo)

    @Benchmark
    fun jacksonDeserialize(): Foo = objectMapper.readValue(fooStr, Foo::class.java)

    @Benchmark
    fun jsonbSerialize(): String = jsonb.toJson(foo)

    @Benchmark
    fun jsonbDeserialize(): Foo = jsonb.fromJson(fooStr, Foo::class.java)
}
