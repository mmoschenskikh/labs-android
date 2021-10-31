package ru.maxultra.lab3p4.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.navigation.NavigationView
import ru.maxultra.lab3p4.AboutActivity
import ru.maxultra.lab3p4.FourthActivity
import ru.maxultra.lab3p4.R

abstract class BaseActivity<VB : ViewBinding>(
    private val inflate: (LayoutInflater) -> VB
) : AppCompatActivity() {

    private var _binding: VB? = null
    protected val binding get() = checkNotNull(_binding) { "Trying to access binding in ${lifecycle.currentState.name} state" }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate(layoutInflater)
        setContentView(binding.root)
        title = this::class.java.simpleName
    }

    protected fun goToActivity(activityClass: Class<out Activity>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }

    protected inner class AboutNavigationDrawerItemListener(private val drawerLayout: DrawerLayout) :
        NavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.nav_about -> {
                    goToActivity(AboutActivity::class.java)
                }
                R.id.nav_fourth -> {
                    val intent = Intent(this@BaseActivity, FourthActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        }
    }
}
