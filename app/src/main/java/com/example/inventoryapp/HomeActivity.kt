package com.example.inventoryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.example.inventoryapp.databinding.ActivityHomeBinding
import com.example.inventoryapp.viewmodels.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private lateinit var navController: NavController
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_home)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
//            if(nd.id == nc.graph.startDestination) {
//
//            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {

        // return navController.navigateUp()
        // return navController.navigateUp(AppBarConfiguration(navController.graph))
        return NavigationUI.navigateUp(navController, AppBarConfiguration(navController.graph))

    }
}