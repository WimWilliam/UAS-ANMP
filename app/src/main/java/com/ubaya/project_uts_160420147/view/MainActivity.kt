package com.ubaya.project_uts_160420147.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ubaya.project_uts_160420147.R
import com.ubaya.project_uts_160420147.viewmodel.SharedViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.HostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Initialize ViewModel
        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]

        // Setup BottomNavigationView with NavController
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnNavigationItemSelectedListener() { item ->
            when (item.itemId) {
                R.id.itemHome -> {
                    navController.navigate(R.id.itemHome)
                    true
                }
                R.id.itemReadHistory -> {
                    // Accessing LiveData directly, ensure it's initialized properly in the ViewModel
                    if (sharedViewModel.lastVisitedGame.value != null) {
                        navController.navigate(R.id.itemReadHistory)
                    } else {
                        Toast.makeText(this, "Please select an item from Home first.", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                R.id.itemProfile -> {
                    navController.navigate(R.id.itemProfile)
                    true
                }
                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.registerFragment -> bottomNavigationView.visibility = View.GONE
                else -> bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}
