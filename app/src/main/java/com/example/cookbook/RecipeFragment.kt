package com.example.cookbook

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import com.example.cookbook.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRecipeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recipe, container, false)

        viewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)


        //binding.recipe = viewModel.recipe.value
        binding.recipe = viewModel.dummyRecipe

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        MenuInflater(context).inflate(R.menu.toolbar_recipe, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(context, "Tapped", Toast.LENGTH_SHORT)
        return when(item.itemId) {
            R.id.action_edit -> {
                Toast.makeText(context, "Edit", Toast.LENGTH_SHORT)
                viewModel.onEditRecipe()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun recipeEdited() {

    }
}
