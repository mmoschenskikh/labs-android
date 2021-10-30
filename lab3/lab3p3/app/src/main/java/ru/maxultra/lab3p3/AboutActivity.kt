package ru.maxultra.lab3p3

import android.os.Bundle
import ru.maxultra.lab3p3.base.BaseActivity
import ru.maxultra.lab3p3.databinding.ActivityAboutBinding

class AboutActivity : BaseActivity<ActivityAboutBinding>(ActivityAboutBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
    }
}
