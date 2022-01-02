package com.douglas.mypersonalfinances

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.douglas.mypersonalfinances.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import java.time.Year

class MainActivity :
    AppCompatActivity(),
    NavController.OnDestinationChangedListener {

    val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_summary, R.id.navigation_transactions
            )
        )
    }
    val navView: BottomNavigationView by lazy {
        binding.navView
    }
    val navController: NavController by lazy {
        (supportFragmentManager
            .findFragmentById(binding.navHostFragment.id) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(this)

        var year = Utils.getYear()

        Log.e(TAG, year.toString())

        var jsonObject = Gson()

        Log.e(TAG, jsonObject.toJson(year))
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination,
                                      arguments: Bundle?) {
        when(destination.id) {
            1 -> {

            }
            2 -> {

            }
            3 -> {

            }
        }
    }
}