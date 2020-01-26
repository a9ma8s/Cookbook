package com.example.cookbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeViewModel : ViewModel() {

    var dummyRecipe = Recipe(
        name = "Rigatoni with Vodka Sauce",
        ingredients = "400 grams flour \n1 tsp salt \n300 ml water",
        instructions = "1. Preheat oven to 200°C \n 2. Mix ingredients \n3. Let dough rest in refrigerator for 30 minutes \n4. Bake for 40-45 minutes",
        image = R.mipmap.rigatoni
    )
}
