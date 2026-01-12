package org.kepocnhh.swapper

import android.app.Application
import org.kepocnhh.swapper.provider.Injection

internal class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // todo
    }

    companion object {
        private var _injection: Injection? = null
        val injection: Injection get() = checkNotNull(_injection) { "No injection!" }
    }
}
