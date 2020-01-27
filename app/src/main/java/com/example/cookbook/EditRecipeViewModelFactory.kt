package com.example.cookbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class EditRecipeViewModelFactory(private val currentRecipe: Recipe): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EditRecipeViewModel::class.java)){
            return EditRecipeViewModel(currentRecipe) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}