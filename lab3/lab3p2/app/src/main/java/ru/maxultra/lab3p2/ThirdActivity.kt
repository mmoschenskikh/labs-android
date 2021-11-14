package ru.maxultra.lab3p2

import android.os.Bundle
import ru.maxultra.lab3p2.base.BaseActivity
import ru.maxultra.lab3p2.databinding.ActivityThirdBinding

class ThirdActivity : BaseActivity<ActivityThirdBinding>(ActivityThirdBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setupNavigationDrawer()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.bnToFirst.setOnClickListener {
            setResult(RESULT_CODE_TO_FIRST)
            finish()
        }
        binding.bnToSecond.setOnClickListener { finish() }
    }

    private fun setupNavigationDrawer() {
        binding.drawerNavView.setNavigationItemSelectedListener(
            AboutNavigationDrawerItemListener(binding.drawerLayout)
        )
    }

    companion object {
        const val RESULT_CODE_TO_FIRST = 66
    }
}
