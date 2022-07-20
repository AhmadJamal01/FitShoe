package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    //requirement10: declaring appBarConfiguration for navController toolbar
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        //requirement 12
        //to my understanding, the up button is only supposed to navigate to a previous fragment, it should not exit the app
        //after logging in, the user should only be able to exit the app, which is already done by the back button
        //to follow the requirements, I decided to hide the up button in the shoe list fragment as well as the login fragment
        //I used this blog post to help in my implementation
        //https://florencelnjeri.medium.com/how-to-remove-up-navigation-from-a-specific-fragment-while-using-android-navigation-library-64153e97e58

        //getting the binding class
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        //setting up the app bar configuration with the login fragment and shoe list fragment as top level destinations
        appBarConfiguration = AppBarConfiguration.Builder(R.id.loginFragment,R.id.shoeListFragment).build()

        //finding the nav host fragment to get the nav controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        //hook up the navigationUI to the actionbar
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
//        return NavigationUI.navigateUp(navController,appBarConfiguration)
        return navController.navigateUp()
    }
}
