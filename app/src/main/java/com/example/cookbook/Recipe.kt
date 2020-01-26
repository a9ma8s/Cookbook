package com.example.cookbook

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Recipe(
    var name: String = "",
    var ingredients: String = "",
    var instructions: String = "",
    var image: Int = 0
)  {
}