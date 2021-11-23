package ru.maxultra.continuewatch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var textSecondsElapsed: TextView
    private var secondsElapsed: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)

        secondsElapsed = savedInstanceState?.getInt(STATE_SECONDS) ?: 0
        textSecondsElapsed.text = getString(R.string.seconds_elapsed_template, secondsElapsed)

        lifecycleScope.launch {
            Log.d(TAG, "Coroutine execution started")
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                Log.d(TAG, "Inner coroutine execution started")
                while (true) {
                    delay(1000L)
                    Log.d(TAG, "Value updated: $secondsElapsed")
                    textSecondsElapsed.text = getString(R.string.seconds_elapsed_template, secondsElapsed++)
                }
            }
        }.apply {
            invokeOnCompletion { Log.d(TAG, "Coroutine execution finished") }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_SECONDS, secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    private companion object {
        private const val STATE_SECONDS = "secondsElapsed"
        private const val TAG = "MainActivity"
    }
}
