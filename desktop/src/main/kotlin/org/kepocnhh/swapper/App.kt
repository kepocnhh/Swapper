package org.kepocnhh.swapper

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.kepocnhh.swapper.module.main.MainScreen
import org.kepocnhh.swapper.provider.FinalLoggers
import org.kepocnhh.swapper.provider.FinalTimes
import org.kepocnhh.swapper.provider.Injection
import org.kepocnhh.swapper.provider.Loggers
import org.kepocnhh.swapper.provider.Times

internal object App {
    private var _injection: Injection? = null
    val injection: Injection get() = checkNotNull(_injection) { "No injection!" }

    init {
        val loggers: Loggers = FinalLoggers
        val times: Times = FinalTimes()
        _injection = Injection(
            loggers = loggers,
            times = times,
        )
    }
}

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication) {
            MainScreen()
        }
    }
}
