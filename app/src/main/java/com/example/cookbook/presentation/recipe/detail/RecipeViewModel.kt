package com.example.cookbook.presentation.recipe.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookbook.R
import com.example.cookbook.Recipe
import com.example.cookbook.data.RecipeDatabaseDao
import kotlinx.coroutines.Job

class RecipeViewModel(recipeKey: Long = 0L, database: RecipeDatabaseDao) : ViewModel() {

    private val viewModelJob = Job()

    private val _currentRecipe = MutableLiveData<Recipe>()
    val currentRecipe: LiveData<Recipe>
    get() = _currentRecipe

    private val _editRecipe = MutableLiveData<Boolean>()
    val editRecipe: LiveData<Boolean>
        get() = _editRecipe

    private val _navigateToRecipeList = MutableLiveData<Boolean>()
    val navigateToRecipeList: LiveData<Boolean>
        get() = _navigateToRecipeList

    init {
        _currentRecipe.value = database.get(recipeKey)
        _editRecipe.value = false
        _navigateToRecipeList.value = false
    }

    var dummyRecipe = Recipe(
        name = "Rigatoni with Vodka Sauce",
        ingredients = "400 grams flour \n1 tsp salt \n300 ml water",
        instructions = "1. Preheat oven to 200°C \n 2. Mix ingredients \n3. Let dough rest in refrigerator for 30 minutes \n4. Bake for 40-45 minutes",
        image = R.mipmap.rigatoni
    )

    fun onEditRecipe() {
        _editRecipe.value = true
    }

    fun onEditRecipeComplete() {
        _editRecipe.value = false
    }

    // TODO button
    fun onNavigateToRecipeList() {
        _navigateToRecipeList.value = true
    }

    fun onNavigateToRecipeListComplete() {
        _navigateToRecipeList.value = false
    }
}
