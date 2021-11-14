package ru.maxultra.lab3p3

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import ru.maxultra.lab3p3.base.BaseActivity
import ru.maxultra.lab3p3.databinding.ActivityThirdBinding

class ThirdActivity : BaseActivity<ActivityThirdBinding>(ActivityThirdBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setupNavigationDrawer()

        binding.bnToFirst.setOnClickListener { goToActivity(MainActivity::class.java) }
        binding.bnToSecond.setOnClickListener { finish() }
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
