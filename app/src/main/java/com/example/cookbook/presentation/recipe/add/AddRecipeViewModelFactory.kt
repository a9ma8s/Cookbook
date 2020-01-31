package com.example.cookbook.presentation.recipe.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.data.RecipeDatabaseDao
import java.lang.IllegalArgumentException

class AddRecipeViewModelFactory(private val dataSource: RecipeDatabaseDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddRecipeViewModel::class.java)){
            return AddRecipeViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}