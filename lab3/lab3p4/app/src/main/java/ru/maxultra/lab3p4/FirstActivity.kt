package ru.maxultra.lab3p4

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import ru.maxultra.lab3p4.base.BaseActivity
import ru.maxultra.lab3p4.databinding.ActivityFirstBinding

class FirstActivity : BaseActivity<ActivityFirstBinding>(ActivityFirstBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setupNavigationDrawer()

        binding.toSecondButton.setOnClickListener { goToActivity(SecondActivity::class.java) }
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
}
