package com.example.cookbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditRecipeViewModel(currentRecipe: Recipe) : ViewModel() {

    private val _cancelEdit = MutableLiveData<Boolean>()
    val cancelEdit: LiveData<Boolean>
        get() = _cancelEdit

    private val _saveEdit = MutableLiveData<Boolean>()
    val saveEdit: LiveData<Boolean>
        get() = _saveEdit

    var recipe = currentRecipe

    init {
        _cancelEdit.value = false
        _saveEdit.value = false
    }

    fun onCancelEdit() {
        _cancelEdit.value = true
    }

    fun onCancelEditComplete() {
        _cancelEdit.value = false
    }

    fun onSaveEdit() {
        _saveEdit.value = true
    }

    fun onSaveEditComplete(){
        _saveEdit.value = false
    }
}
