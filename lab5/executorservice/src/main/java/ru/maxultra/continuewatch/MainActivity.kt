package ru.maxultra.continuewatch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {

    private lateinit var textSecondsElapsed: TextView
    private var secondsElapsed: Int = 0

    private val executorService by lazy { ContinueWatchApp.executorService }
    private var countdownFuture: Future<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)

        secondsElapsed = savedInstanceState?.getInt(STATE_SECONDS) ?: 0
        textSecondsElapsed.text = getString(R.string.seconds_elapsed_template, secondsElapsed)
    }

    override fun onStart() {
        super.onStart()
        val countdownTask = prepareTask()
        countdownFuture = executorService.submit(countdownTask)
    }

    override fun onStop() {
        countdownFuture?.cancel(true)
        countdownFuture = null
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_SECONDS, secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    private fun prepareTask() = Runnable {
        Log.d(TAG, "Task execution started on ${Thread.currentThread().name}")
        try {
            while (Thread.currentThread().isInterrupted.not()) {
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.seconds_elapsed_template, secondsElapsed++)
                }
            }
        } catch (e: InterruptedException) {
            Log.i(TAG, "The thread has been interrupted", e)
        }
        Log.d(TAG, "Task execution finished")
    }

    private companion object {
        private const val STATE_SECONDS = "secondsElapsed"
        private const val TAG = "MainActivity"
    }
}
