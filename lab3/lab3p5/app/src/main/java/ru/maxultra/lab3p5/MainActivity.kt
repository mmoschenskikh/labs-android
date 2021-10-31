package ru.maxultra.lab3p5

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import ru.maxultra.lab3p5.base.BaseActivity
import ru.maxultra.lab3p5.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val conf = AppBarConfiguration(
            setOf(R.id.firstFragment, R.id.secondFragment, R.id.thirdFragment),
            binding.drawerLayout
        )
        binding.navigationView.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController, conf)
    }
}
