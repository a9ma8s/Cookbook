package com.example.cookbook.presentation.recipe.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookbook.R
import com.example.cookbook.Recipe
import com.example.cookbook.data.RecipeDatabaseDao
import kotlinx.coroutines.*

class RecipeViewModel(val recipeKey: Long = 0L, val database: RecipeDatabaseDao) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

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
        initializeRecipe()
        _editRecipe.value = false
        _navigateToRecipeList.value = false
    }

    fun initializeRecipe() {
        uiScope.launch {
            _currentRecipe.value = getRecipeFromDatabase(recipeKey)
        }
    }

    private suspend fun getRecipeFromDatabase(recipeKey: Long): Recipe? {
        return withContext(Dispatchers.IO) {
            var recipe = database.get(recipeKey)
            // TODO add null check
//            if (recipe.name == "") {
//                recipe = null
//            }
            recipe
        }
    }

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
