package com.example.sweet

object RecipeRepository {
    private val recipes = mutableListOf<Recipe>()

    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    fun updateRecipe(recipe: Recipe) {
      //  val index = recipes.indexOfFirst { it.id == recipe.id }
      //  if (index != -1) {
        //    recipe[index] = recipe
   //     }
    }

    fun deleteRecipe(recipe: Recipe) {
      //  recipe.removeIf { it.id == recipe.id }
    }

    fun getRecipes(): List<Recipe> {
        return recipes
    }
}
//איך נדע לסמן מתכון? אין לו תעודת זהות