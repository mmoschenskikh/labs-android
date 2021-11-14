package ru.maxultra.lab3p3

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

        binding.bnToFirst.setOnClickListener { finish() }
        binding.bnToThird.setOnClickListener { goToActivity(ThirdActivity::class.java) }
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

        binding.drawerNavView.setNavigationItemSelectedListener(
            AboutNavigationDrawerItemListener(binding.drawerLayout)
        )
    }
}

