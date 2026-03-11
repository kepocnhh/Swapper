package org.kepocnhh.swapper.provider

import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

internal class FinalTimes(
    private var index: Long = 0,
) : Times {
    override fun now(): Duration {
        return index++.milliseconds
    }
}
