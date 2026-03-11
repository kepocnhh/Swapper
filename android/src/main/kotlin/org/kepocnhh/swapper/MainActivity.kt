package org.kepocnhh.swapper

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ComposeView
import org.kepocnhh.swapper.module.main.MainScreen

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context: Context = this
        val view = ComposeView(context)
        setContentView(view)
        view.setContent {
            MainScreen()
        }
    }
}
