package org.kepocnhh.swapper

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
//import org.kepocnhh.swapper.provider.Injection
//import org.kepocnhh.swapper.provider.Loggers

fun main() {
//    val loggers: Loggers = TODO()
//    val injection: Injection = Injection(
//        loggers = loggers,
//    )
    application {
        Window(onCloseRequest = ::exitApplication) {
            // todo
        }
    }
}
