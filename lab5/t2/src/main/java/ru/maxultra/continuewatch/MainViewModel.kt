package ru.maxultra.continuewatch

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.Future

class MainViewModel : ViewModel() {

    private val executorService by lazy { PictureApp.executorService }

    private val _bitmap = MutableLiveData<Bitmap?>(null)
    val bitmap: LiveData<Bitmap?> = _bitmap

    private var pictureDownloadFuture: Future<*>? = null

    init {
        pictureDownloadFuture = executorService.submit {
            val url = URL("https://upload.wikimedia.org/wikipedia/commons/9/9a/Soyuz_TMA-9_launch.jpg")
            val bm = BitmapFactory.decodeStream(url.openStream())
            _bitmap.postValue(bm)
        }
    }

    override fun onCleared() {
        pictureDownloadFuture?.cancel(true)
        super.onCleared()
    }
}
