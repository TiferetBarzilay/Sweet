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


    @Query("SELECT * FROM recipes WHERE categoryMilky = 1")
    fun getMilkyRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE categoryFur = 1")
    fun getFurRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE categoryCold = 1")
    fun getColdRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE categoryBaked = 1")
    fun getBakedRecipes(): Flow<List<Recipe>>
} 