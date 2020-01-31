package com.example.cookbook

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "recipe")
@Parcelize
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "ingredients")
    var ingredients: String = "",

    @ColumnInfo(name = "instructions")
    var instructions: String = "",

    @ColumnInfo(name = "image")
    var image: Int = R.drawable.ic_broken_image
) : Parcelable {

    // TODO image
    fun updateFields(recipe: Recipe): Recipe {
        this.name = recipe.name
        this.ingredients = recipe.name
        this.instructions = recipe.instructions
//        this.image = recipe.image

        return this
    }

    fun dummyData(): Recipe {
        return Recipe(
            name = "Rigatoni with Vodka Sauce",
            ingredients = "400 grams flour \n1 tsp salt \n300 ml water",
            instructions = "1. Preheat oven to 200°C \n 2. Mix ingredients \n3. Let dough rest in refrigerator for 30 minutes \n4. Bake for 40-45 minutes",
            image = R.mipmap.rigatoni
        )
    }
}