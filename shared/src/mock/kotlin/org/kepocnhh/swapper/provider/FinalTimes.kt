package org.kepocnhh.swapper.provider

import java.util.concurrent.atomic.AtomicLong
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

internal class FinalTimes(initial: Long = 0) : Times {
    private val indices = AtomicLong(initial)

    override fun now(): Duration {
        return indices.incrementAndGet().milliseconds
    }
}
