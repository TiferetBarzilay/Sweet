package com.example.sweet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sweet.Dao.Recipe
import com.example.sweet.Dao.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//מטרתו להחזיק ולנהל נתונים בצורה אסינכרונית (ברקע) בלי שה־UI יקרוס או יאבד את המידע כשמשנים מסך, מסתובבים, וכו'

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val database = RecipeDatabase.getDatabase(application)
    private val recipeDao = database.recipeDao()
   // private val repository = RecipeRepository(recipeDao)


    val allRecipes: LiveData<List<Recipe>> = recipeDao.getAllRecipes().asLiveData()

    /*
    fun getRecipeById(recipeId: String): LiveData<Recipe?> {
       return repository.getById(recipeId)
    }

     */

    fun insertRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeDao.insertRecipe(recipe)
        }
    }

    fun updateRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeDao.updateRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeDao.deleteRecipe(recipe)
        }
    }

}