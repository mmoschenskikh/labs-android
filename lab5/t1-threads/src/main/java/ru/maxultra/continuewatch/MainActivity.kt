package ru.maxultra.continuewatch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textSecondsElapsed: TextView

    private var backgroundThread: Thread? = null
    private var secondsElapsed = 0L
    private var startTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)

        secondsElapsed = savedInstanceState?.getLong(STATE_SECONDS) ?: 0
        textSecondsElapsed.text = getString(R.string.seconds_elapsed_template, secondsElapsed)
    }

    override fun onStart() {
        super.onStart()
        startTime = System.currentTimeMillis()
        backgroundThread = prepareNewThread()
        backgroundThread?.start()
    }

    override fun onStop() {
        backgroundThread?.interrupt()
        backgroundThread = null
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        secondsElapsed = getSecondsElapsedCount()
        outState.putLong(STATE_SECONDS, secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    private fun prepareNewThread() = Thread {
        Log.d(TAG, "Thread execution started")
        try {
            while (Thread.currentThread().isInterrupted.not()) {
                Thread.sleep(1000)
                val timeToDisplay = getSecondsElapsedCount()
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.seconds_elapsed_template, timeToDisplay)
                }
            }
        } catch (e: InterruptedException) {
            Log.i(TAG, "The thread has been interrupted", e)
        }
        Log.d(TAG, "Thread execution finished")
    }

    private fun getSecondsElapsedCount() = (System.currentTimeMillis() - startTime) / 1000 + secondsElapsed

    private companion object {
        private const val STATE_SECONDS = "secondsElapsed"
        private const val TAG = "MainActivity"
    }
}
