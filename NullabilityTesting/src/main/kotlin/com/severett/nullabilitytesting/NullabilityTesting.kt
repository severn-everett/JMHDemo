package com.severett.nullabilitytesting

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.Param
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Warmup
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.*
import kotlin.random.Random

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@Fork(1)
@State(Scope.Benchmark)
open class NullabilityTesting {
    @Param("10000")
    private var repeatAmt = 0

    @Benchmark
    fun nonNullable(blackhole: Blackhole) {
        repeat(repeatAmt) {
            val sum = getNonNullable(Random.nextInt()) + 5
            blackhole.consume(sum)
        }
    }

    @Benchmark
    fun nullable(blackhole: Blackhole) {
        repeat(repeatAmt) {
            val sum = (getNullable(Random.nextInt()) ?: 0) + 5
            blackhole.consume(sum)
        }
    }

    private fun getNonNullable(input: Int) = input

    private fun getNullable(input: Int): Int? = if (input % 2 == 0) input else null
}
