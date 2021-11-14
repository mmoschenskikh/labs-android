package ru.maxultra.lab3p2

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import ru.maxultra.lab3p2.base.BaseActivity
import ru.maxultra.lab3p2.databinding.ActivityFirstBinding

class MainActivity : BaseActivity<ActivityFirstBinding>(ActivityFirstBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setupNavigationDrawer()

        binding.bnToSecond.setOnClickListener { goToActivity(SecondActivity::class.java) }
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
