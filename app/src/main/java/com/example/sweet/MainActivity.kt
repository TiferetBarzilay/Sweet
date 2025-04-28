package com.example.sweet

import android.content.Intent
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
            // setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHostFragment?.navController
        // קישור של ה-BottomNavigationView עם ה-NavController
        navController?.let {
            NavigationUI.setupActionBarWithNavController(
                activity = this,
                navController = it
            )
        }

        //הגדרת ה navBarBottom:
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.main_activity_bottom_view)
        navController?.let { NavigationUI.setupWithNavController(bottomNavigationView, it) }


        // הוספת OnDestinationChangedListener
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
            
            // מסתיר את החץ חזור בפרגמטים ספציפיים
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

        // הסתרת ה-FragmentContainerView וה-bottomNavigationView בהתחלה
        fragmentContainerView.visibility = android.view.View.GONE


        // החבא את הלוגו לאחר 3 שניות והצג את ה-FragmentContainerView
        Handler(Looper.getMainLooper()).postDelayed({

            logoImageView.visibility = ImageView.GONE  // החבאת הלוגו
            fragmentContainerView.visibility = android.view.View.VISIBLE  // הצגת ה-FragmentContainerView
        }, 2000)


    }



//   פונקציה זו  לא קיימת בפרוייקט של חוה! עשינו בשיעור עם יהודה בשיעור 11 וזה בשביל הסרגל התחתון
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