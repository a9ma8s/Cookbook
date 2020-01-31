package com.example.cookbook.presentation.recipe.add

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookbook.BR
import com.example.cookbook.ObservableViewModel
import com.example.cookbook.Recipe
import com.example.cookbook.data.RecipeDatabaseDao
import kotlinx.coroutines.*

class AddRecipeViewModel(val database: RecipeDatabaseDao) : ObservableViewModel() {

    private val vieModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + vieModelJob)

    private val _navigateToRecipeList = MutableLiveData<Boolean>()
    val navigateToRecipeList: LiveData<Boolean>
        get() = _navigateToRecipeList

    private val _navigateToRecipeDetail = MutableLiveData<Boolean>()
    val navigateToRecipeDetail: LiveData<Boolean>
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
        _navigateToRecipeList.value = false
        _navigateToRecipeDetail.value = false
        _recipe.value = Recipe()
    }

    fun onCancel() {
        _navigateToRecipeList.value = true
    }

    fun onCancelComplete() {
        _navigateToRecipeList.value = false
    }

    fun onSave() {
        uiScope.launch {
            // TODO remove !!
            insert(_recipe.value!!)
            _recipe.value = getLatestRecipeFromDatabase()
            _navigateToRecipeDetail.value = true
        }
    }

    fun onSaveComplete() {
        _navigateToRecipeDetail.value = false
    }

    private suspend fun insert(recipe: Recipe) {
        withContext(Dispatchers.IO) {
            database.insert(recipe)
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
