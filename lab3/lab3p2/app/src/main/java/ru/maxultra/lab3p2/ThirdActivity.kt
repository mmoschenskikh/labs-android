package ru.maxultra.lab3p2

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import ru.maxultra.lab3p2.base.BaseActivity
import ru.maxultra.lab3p2.databinding.ActivityThirdBinding

class ThirdActivity : BaseActivity<ActivityThirdBinding>(ActivityThirdBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setupNavigationDrawer()

        binding.toFirstButton.setOnClickListener {
            setResult(RESULT_CODE_TO_FIRST)
            finish()
        }
        binding.toSecondButton.setOnClickListener { finish() }
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

    companion object {
        const val RESULT_CODE_TO_FIRST = 66
    }
}
