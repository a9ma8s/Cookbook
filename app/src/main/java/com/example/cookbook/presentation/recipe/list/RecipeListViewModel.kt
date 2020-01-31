package com.example.cookbook.presentation.recipe.list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookbook.R
import com.example.cookbook.Recipe
import com.example.cookbook.data.RecipeDatabaseDao
import kotlinx.coroutines.*

class RecipeListViewModel(private val database: RecipeDatabaseDao, application: Application) :
    ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var latestRecipe = MutableLiveData<Recipe?>()
    val recipes = database.getAllRecipes()

    private val _navigateToSelectedRecipe = MutableLiveData<Long>()
    val navigateToSelectedRecipe: LiveData<Long>
        get() = _navigateToSelectedRecipe

    private val _navigateToAddRecipe = MutableLiveData<Boolean>()
    val navigateToAddRecipe: LiveData<Boolean>
        get() = _navigateToAddRecipe

    fun onRecipeClicked(id: Long) {
        _navigateToSelectedRecipe.value = id
    }

    fun onRecipeNavigated() {
        _navigateToSelectedRecipe.value = null
    }

    fun onAddRecipe() {
        _navigateToAddRecipe.value = true
    }

    fun onAddRecipeNavigated() {
        _navigateToAddRecipe.value = false
    }

    private fun initializeLatestRecipe() {
        uiScope.launch {
            latestRecipe.value = getLatestRecipeFromDatabase()
        }
    }

    private suspend fun getLatestRecipeFromDatabase(): Recipe? {
        return withContext(Dispatchers.IO) {
            var recipe = database.getLatestRecipe()
            // TODO find better check
            if (recipe?.name == "") {
                recipe = null
            }
            recipe
        }
    }

    init {
        _navigateToAddRecipe.value = false
        initializeLatestRecipe()
    }

    private suspend fun insert(recipe: Recipe) {
        withContext(Dispatchers.IO) {
            database.insert(recipe)
        }
    }

    // TODO find use case
    private suspend fun update(recipe: Recipe) {
        withContext(Dispatchers.IO) {
            database.update(recipe)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onClear() {
        uiScope.launch {
            clear()
            latestRecipe.value = null
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }


}
