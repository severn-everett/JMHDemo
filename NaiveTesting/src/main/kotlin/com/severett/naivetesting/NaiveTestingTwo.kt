package com.severett.naivetesting

import com.severett.common.model.Foo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    val foo = Foo("FUZZ", listOf("BIZZ", "BUZZ"), 20u)
    val timeSource = TimeSource.Monotonic
    val elapsedTime = timeSource.measureTime {
        Json.decodeFromString<Foo>(Json.encodeToString(foo))
    }
    println("KOTLIN: $elapsedTime")
}
