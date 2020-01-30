package com.example.cookbook.presentation.recipe.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.data.RecipeDatabaseDao
import java.lang.IllegalArgumentException

class RecipeViewModelFactory(private val recipeKey: Long, private val dataSource: RecipeDatabaseDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            return RecipeViewModel(recipeKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}