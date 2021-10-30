package ru.maxultra.lab3p4

import android.os.Bundle
import ru.maxultra.lab3p4.base.BaseActivity
import ru.maxultra.lab3p4.databinding.ActivityAboutBinding

class AboutActivity : BaseActivity<ActivityAboutBinding>(ActivityAboutBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
    }
}
