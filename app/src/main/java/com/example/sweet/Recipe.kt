package com.example.sweet

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    var name: String,
    var preparationTime: String,
    var ingredients: String,
    var instructions: String,
    var categoryMilky: Boolean,
    var categoryFur: Boolean,
    var categoryCold: Boolean,
    var categoryBacked: Boolean
 ) : Parcelable
