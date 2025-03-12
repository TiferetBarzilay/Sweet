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
import android.widget.FrameLayout
import androidx.navigation.NavController
//import androidx.navigation.NavController
//import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController  // ה-import של findNavController
import androidx.navigation.ui.setupWithNavController


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var navController: NavController?=null
    var navHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        enableEdgeToEdge()
            // setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //הגדרת ה navBaeBottom:
        val bottomNavigationView = binding.mainActivityBottomView
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHostFragment?.navController


// קישור של ה-BottomNavigationView עם ה-NavController
        navController?.let { bottomNavigationView.setupWithNavController(it) }
       // navController?.let { NavigationUI.setupWithNavController(bottomNavigationView, it) }






        val logoImageView = binding.imgLogoMainActivity
        val fragmentContainerView = binding.mainNavHost

       // binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //זה לא יעבוד כאן כי אין recyclerView ב-mainActivity



        // הוסתרת ה-FragmentContainerView וה-bottomNavigationView בהתחלה
        fragmentContainerView.visibility = android.view.View.GONE
        bottomNavigationView.visibility= BottomNavigationView.GONE


        // החבא את הלוגו לאחר 3 שניות והצג את ה-FragmentContainerView
        Handler(Looper.getMainLooper()).postDelayed({

            logoImageView.visibility = ImageView.GONE  // החבאת הלוגו
            fragmentContainerView.visibility = android.view.View.VISIBLE  // הצגת ה-FragmentContainerView
            bottomNavigationView.visibility= BottomNavigationView.VISIBLE//הצגת הנב בר
        }, 2000)




    }


//    פונקציה זו לא קיימת בפרוייקט של חוה!
//  עשינו בשיעור עם יהודה בשיעור 11
   override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
   }


    override fun onResume() {
        super.onResume()

//יש לסיים!
        //אני חושבת שלא צריך להוסיף כאן יותר, חוה רצתה לעבור מעממוד הראשי לעמוד אחר, אבל אנחנו לא צריכות- יש לנו נביגציה כבר
       // binding.recyclerView.adapter = adapter
        //זה לא יעבוד כאן כי אין recyclerView ב-mainActivity
    }
}