package ru.maxultra.continuewatch

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ContinueWatchApp : Application() {

    companion object {
        val executorService: ExecutorService = Executors.newFixedThreadPool(1)
    }
}
