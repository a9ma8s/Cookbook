package com.example.cookbook.presentation.recipe.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.Recipe
import com.example.cookbook.databinding.ItemRecipeListBinding

class RecipeAdapter(private val clickListener: OnClickListener) :
    ListAdapter<Recipe, RecipeAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: ItemRecipeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Recipe, clickListener: OnClickListener) {
            binding.recipe = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (recipeId: Long) -> Unit) {
        fun onClick(recipe: Recipe) = clickListener(recipe.id)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecipeListBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(recipe)
        }
        holder.bind(recipe, clickListener)
    }
}