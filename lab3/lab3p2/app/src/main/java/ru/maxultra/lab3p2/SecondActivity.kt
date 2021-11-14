package ru.maxultra.lab3p2

import android.content.Intent
import android.os.Bundle
import ru.maxultra.lab3p2.base.BaseActivity
import ru.maxultra.lab3p2.databinding.ActivitySecondBinding

@Suppress("DEPRECATION")
class SecondActivity : BaseActivity<ActivitySecondBinding>(ActivitySecondBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setupNavigationDrawer()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.bnToFirst.setOnClickListener { finish() }
        binding.bnToThird.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_THIRD)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_THIRD) {
            when (resultCode) {
                ThirdActivity.RESULT_CODE_TO_FIRST -> finish()
            }
        }
    }

    private fun setupNavigationDrawer() {
        binding.drawerNavView.setNavigationItemSelectedListener(
            AboutNavigationDrawerItemListener(binding.drawerLayout)
        )
    }

    private companion object {
        private const val REQUEST_CODE_THIRD = 99
    }
}

