package com.example.sweet.Dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var preparationTime: String = "",
    var ingredients: String = "",
    var instructions: String = "",
    var photograph: String = "",
    var userId: String = ""
) : Parcelable
