package com.example.cookbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeViewModel : ViewModel() {

    private val _editRecipe = MutableLiveData<Boolean>()
    val editRecipe: LiveData<Boolean>
        get() = _editRecipe

    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe>
        get() = _recipe

    val dummyRecipe = Recipe(
        name = "Rigatoni with Vodka Sauce",
        ingredients = "400 grams flour \n1 tsp salt \n300 ml water",
        instructions = "1. Preheat oven to 200°C \n 2. Mix ingredients \n3. Let dough rest in refrigerator for 30 minutes \n4. Bake for 40-45 minutes",
        image = R.mipmap.rigatoni
    )

    init {
        _editRecipe.value = false
        _recipe.value = Recipe(
            name = "Rigatoni with Vodka Sauce",
            ingredients = "400 grams flour \n1 tsp salt \n300 ml water",
            instructions = "1. Preheat oven to 200°C \n 2. Mix ingredients \n3. Let dough rest in refrigerator for 30 minutes \n4. Bake for 40-45 minutes",
            image = R.mipmap.rigatoni
        )
    }

    fun onEditRecipe() {
        _editRecipe.value = true
    }


    fun onEditRecipeComplete() {
        _editRecipe.value = false
    }
}
