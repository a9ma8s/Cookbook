package com.example.cookbook.presentation.recipe.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.Recipe
import java.lang.IllegalArgumentException

class EditRecipeViewModelFactory(private val currentRecipe: Recipe): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EditRecipeViewModel::class.java)){
            return EditRecipeViewModel(
                currentRecipe
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}