package org.kepocnhh.swapper

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.kepocnhh.swapper.provider.FinalLoggers
import org.kepocnhh.swapper.provider.FinalTimes
import org.kepocnhh.swapper.provider.Injection
import org.kepocnhh.swapper.provider.Loggers
import org.kepocnhh.swapper.provider.Times

fun main() {
    val loggers: Loggers = FinalLoggers
    val times: Times = FinalTimes()
    val injection = Injection(
        loggers = loggers,
        times = times,
    )
    application {
        Window(onCloseRequest = ::exitApplication) {
            val logger = remember { injection.loggers.create("[App]") }
            LaunchedEffect(Unit) {
                logger.debug("now: ${injection.times.now()}")
            }
        }
    }
}
