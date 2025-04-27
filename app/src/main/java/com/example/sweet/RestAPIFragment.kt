package com.example.sweet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet.Dao.Recipe
import com.example.sweet.databinding.FragmentRestAPIBinding
import com.example.sweet.model.Meal
import com.example.sweet.model.MealAdapter
import com.example.sweet.model.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RestAPIFragment : Fragment() {
    private lateinit var binding:FragmentRestAPIBinding
    private lateinit var mealAdapter: MealAdapter
    private lateinit var mealRecyclerView: RecyclerView
    private var mealList: List<Meal> = emptyList()

    // ניצור את Service של Retrofit
    private val mealApi = RetrofitInstance.mealApi


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRestAPIBinding.inflate(inflater,container,false)

        // הגדרת ה-RecyclerView
        mealRecyclerView = binding.mealRecyclerView

        // יצירת ה-Adapter של המתכונים
        mealAdapter = MealAdapter(mealList) { meal ->
            // כאן המשתמש לחץ על meal
            val mealUrl = "https://www.themealdb.com/meal/${meal.idMeal}"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(mealUrl)
            startActivity(intent)
        }
        mealRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mealRecyclerView.adapter = mealAdapter

        // קריאה ל-API והצגת המתכונים ב-RecyclerView
        loadMeals()

        return binding.root
    }

    private fun loadMeals() {
        // קריאה ל-API בתוך Coroutine (כיוון ש-Retrofit עובד באופן אסינכרוני)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // בקשת המתכונים
                val response = RetrofitInstance.mealApi.getMeals()

                // עדכון רשימת המתכונים
                if (response.meals != null && response.meals.isNotEmpty()) {
                    mealList = response.meals
                    mealAdapter = MealAdapter(mealList) { meal ->
                        // כאן המשתמש לחץ על meal
                        val mealUrl = "https://www.themealdb.com/meal/${meal.idMeal}"
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(mealUrl)
                        startActivity(intent)
                    }
                    mealRecyclerView.adapter = mealAdapter
                } else {
                    // הצגת הודעה אם אין נתונים
                    Toast.makeText(context, "לא נמצאו מתכונים", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                // טיפול בשגיאות (אם לא מצליחים להתחבר ל-API)
                val errorMessage = when (e) {
                    is java.net.UnknownHostException -> "בעיה בחיבור לאינטרנט"
                    is java.net.SocketTimeoutException -> "החיבור לשרת נכשל - נסה שוב"
                    is retrofit2.HttpException -> "שגיאת שרת: ${e.code()}"
                    else -> "שגיאה בהתחברות ל-API: ${e.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                Log.e("API Error", "Error fetching meals", e)
            }
        }
    }

}
