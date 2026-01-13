package org.kepocnhh.swapper

import android.app.Application
import org.kepocnhh.swapper.provider.FinalLoggers
import org.kepocnhh.swapper.provider.Injection
import org.kepocnhh.swapper.provider.Loggers

internal class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val loggers: Loggers = FinalLoggers
        val logger = loggers.create("[App]")
        logger.debug("create")
        _injection = Injection(
            loggers = loggers,
        )
    }

    companion object {
        private var _injection: Injection? = null
        val injection: Injection get() = checkNotNull(_injection) { "No injection!" }
    }
}
