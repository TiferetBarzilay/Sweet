package com.example.sweet

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.sweet.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var navController: NavController?=null
    var navHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            FirebaseApp.initializeApp(this)
            Log.d("FirebaseApp1","init")


        val toolbar=binding.toolbar
        setSupportActionBar(toolbar)


        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHostFragment?.navController
        navController?.let {
            NavigationUI.setupActionBarWithNavController(
                activity = this,
                navController = it
            )
        }
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            navController?.navigate(R.id.action_sweetAppFragment_to_homeFragment)
        }


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.main_activity_bottom_view)
        navController?.let { NavigationUI.setupWithNavController(bottomNavigationView, it) }


        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.sweetAppFragment, R.id.registrationFragment, R.id.logInFragment, R.id.newPostUploadFragment, R.id.editPostFragment -> {
                    bottomNavigationView.visibility = BottomNavigationView.GONE
                }
                else -> {
                    bottomNavigationView.visibility = BottomNavigationView.VISIBLE
                }
            }
            when (destination.id) {
                R.id.sweetAppFragment -> {
                    toolbar.visibility = View.GONE
                }
                else -> {
                    toolbar.visibility = View.VISIBLE
                }
            }
            
            when (destination.id) {
                R.id.homeFragment, R.id.personalAreaFragment, R.id.restAPIFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                else -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }
            }
        }



        val logoImageView = binding.imgLogoMainActivity
        val fragmentContainerView = binding.mainNavHost

        fragmentContainerView.visibility = View.GONE
        bottomNavigationView.visibility = View.GONE

        Handler(Looper.getMainLooper()).postDelayed({

            logoImageView.visibility = ImageView.GONE
            fragmentContainerView.visibility = View.VISIBLE
            bottomNavigationView.visibility = View.VISIBLE
        }, 2000)


    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onResume() {
        super.onResume()

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() ?: super.onSupportNavigateUp()
    }
}