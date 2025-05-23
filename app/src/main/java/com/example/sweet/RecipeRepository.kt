package com.example.sweet

import android.net.Uri
import com.example.sweet.Dao.Recipe
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID



object RecipeRepository {
    private val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()


    fun addRecipe(recipe: Recipe, onSuccess: (Void?) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("recipes")
            .add(recipe)
            .addOnSuccessListener {
                onSuccess(null)
            }
            .addOnFailureListener(onFailure)
    }

    fun updateRecipe(recipe: Recipe, onSuccess: (Void?) -> Unit, onFailure: (Exception) -> Unit) {

        db.collection("recipes")
            .document(recipe.id)
            .set(recipe)
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onFailure)
    }

    fun deleteRecipe(recipe: Recipe, onSuccess: (Void?) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("recipes")
            .document(recipe.id)
            .delete()
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onFailure)

    }

    fun getRecipes(onSuccess: (List<Recipe>) -> Unit, onFailure: (Exception) -> Unit) {
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

    fun uploadImageToFirebaseStorage(
        imageUri: Uri,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val filename = UUID.randomUUID().toString() + ".jpg"
        val ref = storage.reference.child("recipe_images/$filename")

        ref.putFile(imageUri)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener { uri ->
                    onSuccess(uri.toString())
                }.addOnFailureListener { exception ->
                    onFailure(exception)
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    fun getRecipesByUserId(
        userId: String,
        onSuccess: (List<Recipe>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val db = FirebaseFirestore.getInstance()
        db.collection("recipes")
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { result ->
                val recipes = result.map { document ->
                    document.toObject(Recipe::class.java).copy(id = document.id)
                }
                onSuccess(recipes)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }


}
