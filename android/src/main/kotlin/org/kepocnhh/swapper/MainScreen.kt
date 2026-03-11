package org.kepocnhh.swapper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
internal fun MainScreen() {
    val injection = remember { App.injection }
    val logger = remember { injection.loggers.create("[App]") }
    LaunchedEffect(Unit) {
        logger.debug("now: ${injection.times.now()}")
    }
}
