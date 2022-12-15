package com.severett.naivetesting

import com.severett.common.model.Foo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val MS_DIVISOR = 1_000_000

fun main() {
    val foo = Foo("FUZZ", listOf("BIZZ", "BUZZ"), 20u)
    // Kotlin
    val kotlinStart = System.nanoTime()
    Json.decodeFromString<Foo>(Json.encodeToString(foo))
    println("KOTLIN: ${(System.nanoTime() - kotlinStart) / MS_DIVISOR}ms")
}
