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
import com.example.cookbook.*
import com.example.cookbook.databinding.FragmentEditRecipeBinding


class EditRecipeFragment : Fragment() {

    private lateinit var viewModelFactory: EditRecipeViewModelFactory
    private lateinit var viewModel: EditRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentEditRecipeBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_edit_recipe, container, false)

        // TODO remove !!
        viewModelFactory =
            EditRecipeViewModelFactory(
                EditRecipeFragmentArgs.fromBundle(
                    arguments!!
                ).recipe
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(EditRecipeViewModel::class.java)

        binding.recipe = viewModel.recipe
        binding.toolbarEditRecipe.setOnMenuItemClickListener { onClickToolbar(it) }
        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.cancelEdit.observe(viewLifecycleOwner, Observer { cancelEdit ->
            if (cancelEdit) {
                // TODO
                findNavController().navigate(EditRecipeFragmentDirections.actionEditRecipeFragmentToRecipeFragment(viewModel.recipe.id))
                //findNavController().navigate(EditRecipeFragmentDirections.actionEditRecipeFragmentToRecipeFragment(viewModel.recipe))
                viewModel.onCancelEditComplete()
            }
        })

        viewModel.saveEdit.observe(viewLifecycleOwner, Observer { saveEdit ->
            if (saveEdit) {
                // TODO update recipe
                //TODO
                findNavController().navigate(EditRecipeFragmentDirections.actionEditRecipeFragmentToRecipeFragment(viewModel.recipe.id))
                //findNavController().navigate(EditRecipeFragmentDirections.actionEditRecipeFragmentToRecipeFragment(viewModel.recipe))
                viewModel.onSaveEditComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun onClickToolbar(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cancel -> {
                viewModel.onCancelEdit()
                true
            }
            else -> true
        }
    }

}
