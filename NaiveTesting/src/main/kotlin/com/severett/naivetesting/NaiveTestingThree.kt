package com.severett.naivetesting

import com.severett.common.model.Foo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource
import kotlin.time.measureTime

private const val MICRO_DIVISOR = 1_000
private const val ITERATIONS = 10_000

@OptIn(ExperimentalTime::class)
fun main() {
    val foo = Foo("FUZZ", listOf("BIZZ", "BUZZ"), 20u)
    // Warmup
    repeat(ITERATIONS) {
        Json.decodeFromString<Foo>(Json.encodeToString(foo))
    }
    // Testing
    val timeSource = TimeSource.Monotonic
    val elapsedTime = (0 until ITERATIONS).fold(0L) { totalTime, _ ->
        val measuredTime = timeSource.measureTime {
            Json.decodeFromString<Foo>(Json.encodeToString(foo))
        }.inWholeNanoseconds
        totalTime + measuredTime
    }.toDouble()
    println("KOTLIN: ${(elapsedTime / ITERATIONS) / MICRO_DIVISOR}µs")
}
