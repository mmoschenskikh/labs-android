package ru.maxultra.continuewatch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textSecondsElapsed: TextView

    private var backgroundThread: Thread? = null

    private var secondsElapsedBeforeLastOnStart = 0L
    private var lastOnStartCallTime = 0L
    private var lastTimeToDisplay: Long = 0L

    private val timeToDisplay: Long
        get() = (System.currentTimeMillis() - lastOnStartCallTime) / 1000 + secondsElapsedBeforeLastOnStart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)

        lastTimeToDisplay = savedInstanceState?.getLong(STATE_SECONDS) ?: 0
        textSecondsElapsed.text = getString(R.string.seconds_elapsed_template, lastTimeToDisplay)
    }

    override fun onStart() {
        super.onStart()
        secondsElapsedBeforeLastOnStart = lastTimeToDisplay
        lastOnStartCallTime = System.currentTimeMillis()
        backgroundThread = prepareNewThread()
        backgroundThread?.start()
    }

    override fun onStop() {
        backgroundThread?.interrupt()
        backgroundThread = null
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(STATE_SECONDS, lastTimeToDisplay)
        super.onSaveInstanceState(outState)
    }

    private fun prepareNewThread() = Thread {
        Log.d(TAG, "Thread execution started")
        try {
            while (Thread.currentThread().isInterrupted.not()) {
                Thread.sleep(1000)
                lastTimeToDisplay = timeToDisplay
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.seconds_elapsed_template, lastTimeToDisplay)
                }
            }
        } catch (e: InterruptedException) {
            Log.i(TAG, "The thread has been interrupted", e)
        }
        Log.d(TAG, "Thread execution finished")
    }

    private companion object {
        private const val STATE_SECONDS = "secondsElapsed"
        private const val TAG = "MainActivity"
    }
}
