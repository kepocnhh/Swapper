package org.kepocnhh.swapper

import android.app.Application
import org.kepocnhh.swapper.provider.FinalLoggers
import org.kepocnhh.swapper.provider.FinalTimes
import org.kepocnhh.swapper.provider.Injection
import org.kepocnhh.swapper.provider.Loggers
import org.kepocnhh.swapper.provider.Times

internal class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val loggers: Loggers = FinalLoggers
        val times: Times = FinalTimes()
        _injection = Injection(
            loggers = loggers,
            times = times,
        )
    }

    companion object {
        private var _injection: Injection? = null
        val injection: Injection get() = checkNotNull(_injection) { "No injection!" }
    }
}
