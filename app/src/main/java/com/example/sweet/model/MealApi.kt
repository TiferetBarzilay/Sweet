package com.example.sweet.model

import retrofit2.http.GET

interface MealApi {
    @GET("search.php?s=")
    suspend fun getMeals(): MealResponse
}
