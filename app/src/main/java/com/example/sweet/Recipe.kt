package com.example.sweet

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    var id: String="",
    var name: String="",
    var preparationTime: String="",
    var ingredients: ArrayList<String> = arrayListOf(),
    var instructions: String="",
    var categoryMilky: Boolean=false,
    var categoryFur: Boolean=false,
    var categoryCold: Boolean=false,
    var categoryBacked: Boolean=false,
    var isFavorite:Boolean=false
 ) : Parcelable
