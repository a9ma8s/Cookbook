package com.example.cookbook.presentation.recipe.edit

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookbook.BR
import com.example.cookbook.ObservableViewModel
import com.example.cookbook.Recipe
import com.example.cookbook.data.RecipeDatabaseDao
import kotlinx.coroutines.*

class EditRecipeViewModel(val recipeKey: Long = 0L, val database: RecipeDatabaseDao) :
    ObservableViewModel() {

    private val vieModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + vieModelJob)

    private val _navigateToRecipeDetail = MutableLiveData<Boolean>()
    val navigateToRecipeDetail : LiveData<Boolean>
        get() = _navigateToRecipeDetail


    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe>
        get() = _recipe

    // TODO remove !!
    @Bindable
    fun getName(): String {
        return _recipe.value!!.name
    }

    // TODO remove !!
    fun setName(value: String) {
        if (_recipe.value!!.name != value) {
            _recipe.value!!.name = value
            notifyPropertyChanged(BR.recipe)
        }
    }

    // TODO remove !!
    @Bindable
    fun getIngredients(): String {
        return _recipe.value!!.ingredients
    }

    // TODO remove !!
    fun setIngredients(value: String) {
        if (_recipe.value!!.ingredients != value) {
            _recipe.value!!.ingredients = value
            notifyPropertyChanged(BR.recipe)
        }
    }

    // TODO remove !!
    @Bindable
    fun getInstructions(): String {
        return _recipe.value!!.instructions
    }

    // TODO remove !!
    fun setInstructions(value: String) {
        if (_recipe.value!!.instructions != value) {
            _recipe.value!!.instructions = value
            notifyPropertyChanged(BR.recipe)
        }
    }

    // TODO bindable for image

    init {
        _navigateToRecipeDetail.value = false
        _recipe.value = Recipe()
    }

    init {
        _navigateToRecipeDetail.value = false
        initializeRecipe()
    }

    fun initializeRecipe() {
        uiScope.launch {
            _recipe.value = getRecipeFromDatabase(recipeKey)
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

    fun onCancel() {
        _navigateToRecipeDetail.value = true
    }

    fun onCancelComplete() {
        _navigateToRecipeDetail.value = false
    }

    fun onSave() {
        uiScope.launch {
            // TODO remove !!
            update(_recipe.value!!)
            _navigateToRecipeDetail.value = true
        }
    }

    fun onSaveComplete() {
        _navigateToRecipeDetail.value = false
    }

    private suspend fun update(recipe: Recipe) {
        withContext(Dispatchers.IO) {
            database.update(recipe)
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
}
