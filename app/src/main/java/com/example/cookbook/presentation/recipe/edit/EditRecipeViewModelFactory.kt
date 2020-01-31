package com.example.cookbook.presentation.recipe.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.data.RecipeDatabaseDao
import java.lang.IllegalArgumentException

class EditRecipeViewModelFactory(private val recipeKey: Long, private val dataSource: RecipeDatabaseDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EditRecipeViewModel::class.java)){
            return EditRecipeViewModel(
                recipeKey, dataSource
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}