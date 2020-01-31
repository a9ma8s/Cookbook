package com.example.cookbook.presentation.recipe.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.cookbook.R
import com.example.cookbook.Recipe
import com.example.cookbook.data.RecipeDatabase
import com.example.cookbook.databinding.FragmentAddRecipeBinding

class AddRecipeFragment : Fragment() {

    private lateinit var binding: FragmentAddRecipeBinding
    private lateinit var viewModelFactory: AddRecipeViewModelFactory
    private lateinit var viewModel: AddRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_recipe, container, false)

        val application = requireNotNull(activity).application
        val dataSource = RecipeDatabase.getInstance(application).recipeDatabaseDao

        // TODO remove !!
        viewModelFactory =
            AddRecipeViewModelFactory(
                dataSource
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddRecipeViewModel::class.java)

        binding.viewModel = viewModel
        binding.toolbarAddRecipe.setOnMenuItemClickListener { onClickToolbar(it) }

        binding.lifecycleOwner = this

        viewModel.navigateToRecipeList.observe(viewLifecycleOwner, Observer { cancelAddRecipe ->
            if (cancelAddRecipe) {
                findNavController().navigate(
                    AddRecipeFragmentDirections.actionAddRecipeFragmentToRecipeListFragment()
                )
                viewModel.onCancelComplete()
            }
        })

        viewModel.navigateToRecipeDetail.observe(viewLifecycleOwner, Observer { saveRecipe ->
            if(saveRecipe) {
                // TODO remove !!
                findNavController().navigate(
                    AddRecipeFragmentDirections.actionAddRecipeFragmentToRecipeFragment(viewModel.recipe.value!!.id)
                )
                viewModel.onSaveComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun onClickToolbar(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cancel -> {
                viewModel.onCancel()
                true
            }
            else -> true
        }
    }

//    // TODO image
//    fun saveRecipe() {
//        val newRecipe = Recipe(
//            name = binding.textEditName.toString(),
//            ingredients = binding.textEditIngredients.toString(),
//            instructions = binding.textEditInstructions.toString()
//        )
//
//        viewModel.saveRecipe(newRecipe)
//
//        // TODO clear text fields?
//    }

}