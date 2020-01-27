package com.example.cookbook

import androidx.lifecycle.ViewModel

class EditRecipeViewModel(currentRecipe: Recipe) : ViewModel() {
    var recipe = currentRecipe
}
