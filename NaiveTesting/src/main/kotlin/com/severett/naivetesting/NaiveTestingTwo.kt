package com.severett.naivetesting

import com.severett.common.model.Foo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.time.TimeSource
import kotlin.time.measureTime

fun main() {
    val foo = Foo("FUZZ", listOf("BIZZ", "BUZZ"), 20u)
    val timeSource = TimeSource.Monotonic
    val elapsedTime = timeSource.measureTime {
        Json.decodeFromString<Foo>(Json.encodeToString(foo))
    }
    println("KOTLIN: $elapsedTime")
}
