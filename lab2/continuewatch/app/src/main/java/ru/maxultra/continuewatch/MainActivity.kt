package ru.maxultra.continuewatch

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView

    private var isCounting = false

    private val prefs: SharedPreferences? by lazy { getPreferences(Context.MODE_PRIVATE) }

    private var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            textSecondsElapsed.post {
                if (isCounting) {
                    textSecondsElapsed.text =
                        getString(R.string.seconds_elapsed_template, secondsElapsed++)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        backgroundThread.start()
    }

    override fun onStart() {
        super.onStart()
        secondsElapsed = prefs?.getInt(STATE_SECONDS, 0) ?: 0
        textSecondsElapsed.text = getString(R.string.seconds_elapsed_template, secondsElapsed)
        isCounting = true
    }

    override fun onStop() {
        isCounting = false
        prefs?.edit()
            ?.putInt(STATE_SECONDS, secondsElapsed)
            ?.apply()
        super.onStop()
    }

    private companion object {
        private const val STATE_SECONDS = "secondsElapsed"
    }
}
