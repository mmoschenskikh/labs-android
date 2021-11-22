package ru.maxultra.continuewatch

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainViewModel : ViewModel() {

    private val _bitmap = MutableLiveData<Bitmap?>(null)
    val bitmap: LiveData<Bitmap?> = _bitmap

    init {
        viewModelScope.launch {
            _bitmap.value = withContext(Dispatchers.IO) {
                val url = URL("https://upload.wikimedia.org/wikipedia/commons/9/9a/Soyuz_TMA-9_launch.jpg")
                val stream = url.openStream()
                BitmapFactory.decodeStream(stream)
            }
        }
    }
}
