package com.example.sweet.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    fun getRecipeById(recipeId: String): LiveData<Recipe?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: Recipe): Long

    @Update
    fun updateRecipe(recipe: Recipe): Int

    @Delete
    fun deleteRecipe(recipe: Recipe): Int
} 