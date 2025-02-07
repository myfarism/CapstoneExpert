package com.example.capstoneexpert

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.capstoneexpert.databinding.ActivityMainBinding
import com.example.capstoneexpert.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var homeMenuItem: MenuItem? = null
    private var favoriteMenuItem: MenuItem? = null
    private var dateMenuItem: MenuItem? = null
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            loadHomeFragment()
        }
    }

    private fun loadHomeFragment() {
        val homeFragment = HomeFragment()
        currentFragment = homeFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, homeFragment)
            .commit()
        supportActionBar?.title = getString(R.string.app_name)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        homeMenuItem = menu.findItem(R.id.nav_home)
        favoriteMenuItem = menu.findItem(R.id.nav_favorite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_home -> {
                loadHomeFragment()
                updateMenuIcons(R.id.nav_home)
                true
            }
            R.id.nav_favorite -> {
                try {
                    val fragmentClass = Class.forName("com.example.capstoneexpert.favourite.FavFragment")
                    val favoriteFragment = fragmentClass.newInstance() as Fragment
                    currentFragment = favoriteFragment

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, favoriteFragment)
                        .commit()

                    supportActionBar?.title = getString(R.string.menu_favorite)
                    updateMenuIcons(R.id.nav_favorite)
                } catch (e: ClassNotFoundException) {
                    Toast.makeText(this, "Fitur Favorit belum terinstall", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateMenuIcons(selectedItemId: Int) {
        when (selectedItemId) {
            R.id.nav_home -> {
                homeMenuItem?.setIcon(R.drawable.ic_home_filled)
                favoriteMenuItem?.setIcon(R.drawable.ic_not_favorite_white)
                dateMenuItem?.setIcon(R.drawable.ic_date_unfilled)
            }
            R.id.nav_favorite -> {
                homeMenuItem?.setIcon(R.drawable.ic_home_unfilled)
                favoriteMenuItem?.setIcon(R.drawable.ic_favorite_white)
                dateMenuItem?.setIcon(R.drawable.ic_date_unfilled)
            }
        }
    }
}
