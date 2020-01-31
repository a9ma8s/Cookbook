package com.example.cookbook.presentation.recipe.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cookbook.R
import com.example.cookbook.Recipe
import com.example.cookbook.data.RecipeDatabase
import com.example.cookbook.databinding.FragmentEditRecipeBinding


class EditRecipeFragment : Fragment() {

    // TODO binding as lateinit?
    private lateinit var binding: FragmentEditRecipeBinding
    private lateinit var viewModelFactory: EditRecipeViewModelFactory
    private lateinit var viewModel: EditRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_recipe, container, false)

        val application = requireNotNull(activity).application
        val dataSource = RecipeDatabase.getInstance(application).recipeDatabaseDao

        // TODO remove !!
        viewModelFactory =
            EditRecipeViewModelFactory(
                EditRecipeFragmentArgs.fromBundle(arguments!!).recipe,
                dataSource
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(EditRecipeViewModel::class.java)

        binding.viewModel = viewModel
        binding.toolbarEditRecipe.setOnMenuItemClickListener { onClickToolbar(it) }

        binding.lifecycleOwner = this

        viewModel.navigateToRecipeDetail.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                findNavController().navigate(
                    EditRecipeFragmentDirections.actionEditRecipeFragmentToRecipeFragment(viewModel.recipeKey)
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

}
