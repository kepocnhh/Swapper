package org.kepocnhh.swapper.module.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kepocnhh.swapper.App

@Composable
internal fun MainScreen() {
    val injection = remember { App.injection }
    val logger = remember { injection.loggers.create("[Main]") }
    LaunchedEffect(Unit) {
        logger.debug("now: ${injection.times.now()}")
    }
    val nowState = remember { mutableStateOf(injection.times.now()) }
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
        ) {
            BasicText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = "now: ${nowState.value}",
            )
            BasicText(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clickable {
                        coroutineScope.launch {
                            val now = withContext(Dispatchers.Default) {
                                injection.times.now()
                            }
                            nowState.value = now
                        }
                    }
                    .wrapContentSize(),
                text = "click",
            )
        }
    }
}
