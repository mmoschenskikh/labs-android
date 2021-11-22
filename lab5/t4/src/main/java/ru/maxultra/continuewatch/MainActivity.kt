package ru.maxultra.continuewatch

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.imageView)
        Picasso.get()
            .load("https://upload.wikimedia.org/wikipedia/commons/9/9a/Soyuz_TMA-9_launch.jpg")
            .into(imageView)
    }
}
