package ru.maxultra.continuewatch

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.imageView)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.bitmap.observe(this) { bitmap ->
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap)
            }
        }
    }
}
