package com.example.cookbook.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cookbook.Recipe

@Dao
interface RecipeDatabaseDao {
    @Insert
    fun insert(recipe: Recipe)

    @Update
    fun update(recipe: Recipe)

    @Query("SELECT * FROM recipe WHERE id = :key")
    fun get(key: Long): Recipe?

    @Query("SELECT * FROM recipe ORDER BY id DESC LIMIT 1")
    fun getLatestRecipe(): Recipe?

    @Query("SELECT * FROM recipe ORDER BY id")
    fun getAllRecipes(): LiveData<List<Recipe>>
}