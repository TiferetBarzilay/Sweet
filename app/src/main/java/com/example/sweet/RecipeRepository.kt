package com.example.sweet

import com.example.sweet.Dao.Recipe
import com.example.sweet.Dao.RecipeDao
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeDao: RecipeDao) {

    val allRecipes: Flow<List<Recipe>> = recipeDao.getAllRecipes()
    val favoriteRecipes: Flow<List<Recipe>> = recipeDao.getFavoriteRecipes()

    suspend fun insert(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun update(recipe: Recipe) {
        recipeDao.updateRecipe(recipe)
    }

    suspend fun delete(recipe: Recipe) {
        recipeDao.deleteRecipe(recipe)
    }

    suspend fun getById(id: String): Recipe? {
        return recipeDao.getRecipeById(id)
    }

    fun getMilkyRecipes(): Flow<List<Recipe>> = recipeDao.getMilkyRecipes()
    fun getFurRecipes(): Flow<List<Recipe>> = recipeDao.getFurRecipes()
    fun getColdRecipes(): Flow<List<Recipe>> = recipeDao.getColdRecipes()
    fun getBakedRecipes(): Flow<List<Recipe>> = recipeDao.getBakedRecipes()
}


/*
object RecipeRepository {
    private val db = Firebase.firestore
    //private val recipes = mutableListOf<Recipe>()

    fun addRecipe(recipe: Recipe, onSuccess: (Void?) -> Unit, onFailure: (Exception) -> Unit) {
        //recipes.add(recipe)
        db.collection("recipes")
            .add(recipe)
            .addOnSuccessListener { documentReference ->
                val recipeWithId = recipe.copy(id = documentReference.id)
                updateRecipe(recipeWithId, onSuccess, onFailure)
            }
            .addOnFailureListener(onFailure)
    }

    fun updateRecipe(recipe: Recipe, onSuccess: (Void?) -> Unit, onFailure: (Exception) -> Unit) {
      //  val index = recipes.indexOfFirst { it.id == recipe.id }
      //  if (index != -1) {
        //    recipe[index] = recipe
   //     }
        db.collection("recipes")
            .document(recipe.id)
            .set(recipe)
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onFailure)
    }

    fun deleteRecipe(recipe: Recipe, onSuccess: (Void?) -> Unit, onFailure: (Exception) -> Unit) {
      //  recipe.removeIf { it.id == recipe.id }
        db.collection("recipes")
            .document(recipe.id)
            .delete()
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onFailure)

    }

    fun getRecipes(onSuccess: (List<Recipe>) -> Unit, onFailure: (Exception) -> Unit) {
        //return recipes
        db.collection("recipes")
            .get()
            .addOnSuccessListener { result ->
                val recipes = result.map { document ->
                    document.toObject(Recipe::class.java).copy(id = document.id)
                }
                onSuccess(recipes)
            }
            .addOnFailureListener(onFailure)
    }

}

 */
