package ru.maxultra.lab3p2

import android.os.Bundle
import ru.maxultra.lab3p2.base.BaseActivity
import ru.maxultra.lab3p2.databinding.ActivityAboutBinding

class AboutActivity : BaseActivity<ActivityAboutBinding>(ActivityAboutBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
    }
}
