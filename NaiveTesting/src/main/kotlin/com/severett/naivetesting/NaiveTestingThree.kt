package com.severett.naivetesting

import com.severett.common.model.Foo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val MICRO_DIVISOR = 1_000
private const val ITERATIONS = 10_000

fun main() {
    val foo = Foo("FUZZ", listOf("BIZZ", "BUZZ"), 20u)
    // Warmup
    repeat(ITERATIONS) {
        Json.decodeFromString<Foo>(Json.encodeToString(foo))
    }
    // Testing
    val elapsedTime = (0 until ITERATIONS).fold(0L) { totalTime, _ ->
        val start = System.nanoTime()
        Json.decodeFromString<Foo>(Json.encodeToString(foo))
        totalTime + (System.nanoTime() - start)
    }
    println("KOTLIN: ${(elapsedTime / ITERATIONS) / MICRO_DIVISOR}µs")
}
