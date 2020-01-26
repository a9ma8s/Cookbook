package com.example.cookbook

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.example.cookbook.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRecipeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe, container, false)

        viewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)

        binding.recipe = viewModel.dummyRecipe

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val toolbarMenu = R.menu.toolbar_recipe
        inflater?.inflate(toolbarMenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
//                view?.findNavController()?.navigate(RecipeFragmentDirections.actionRecipeFragmentToEditRecipeFragment(
//                    viewModel.currentRecipe.value!!
//                ))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
