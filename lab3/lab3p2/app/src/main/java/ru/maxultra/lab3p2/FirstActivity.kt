package ru.maxultra.lab3p2

import android.content.Intent
import android.os.Bundle
import ru.maxultra.lab3p2.base.BaseActivity
import ru.maxultra.lab3p2.databinding.ActivityFirstBinding

class FirstActivity : BaseActivity<ActivityFirstBinding>(ActivityFirstBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toSecondButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}
