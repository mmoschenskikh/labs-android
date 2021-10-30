package ru.maxultra.lab3p3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import ru.maxultra.lab3p3.base.BaseActivity
import ru.maxultra.lab3p3.databinding.ActivitySecondBinding

@Suppress("DEPRECATION")
class SecondActivity : BaseActivity<ActivitySecondBinding>(ActivitySecondBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setupNavigationDrawer()

        binding.toFirstButton.setOnClickListener { finish() }
        binding.toThirdButton.setOnClickListener {
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
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.open_drawer_action,
            R.string.close_drawer_action
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener(
            AboutNavigationDrawerItemListener(binding.drawerLayout)
        )
    }

    private companion object {
        private const val REQUEST_CODE_THIRD = 99
    }
}

