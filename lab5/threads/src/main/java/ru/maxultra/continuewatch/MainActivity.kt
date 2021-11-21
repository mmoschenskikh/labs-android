package ru.maxultra.continuewatch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textSecondsElapsed: TextView

    private var backgroundThread: Thread? = null
    private var secondsElapsed = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)

        secondsElapsed = savedInstanceState?.getInt(STATE_SECONDS) ?: 0
        textSecondsElapsed.text = getString(R.string.seconds_elapsed_template, secondsElapsed)
    }

    override fun onStart() {
        super.onStart()
        backgroundThread = prepareNewThread()
        backgroundThread?.start()
    }

    override fun onStop() {
        backgroundThread?.interrupt()
        backgroundThread = null
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_SECONDS, secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    private fun prepareNewThread() = Thread {
        Log.d(TAG, "Thread execution started")
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
        Log.d(TAG, "Thread execution finished")
    }

    private companion object {
        private const val STATE_SECONDS = "secondsElapsed"
        private const val TAG = "MainActivity"
    }
}
