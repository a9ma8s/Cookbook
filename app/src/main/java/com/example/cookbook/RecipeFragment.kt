package com.example.cookbook

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
import com.example.cookbook.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRecipeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recipe, container, false)

        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        binding.viewModel = viewModel
        binding.recipe = viewModel.dummyRecipe
        binding.toolbarRecipe.setOnMenuItemClickListener { onClickToolbar(it) }

        viewModel.editRecipe.observe(viewLifecycleOwner, Observer { editRecipe ->
            if (editRecipe) {
                findNavController().navigate(RecipeFragmentDirections.actionRecipeFragmentToEditRecipeFragment(viewModel.dummyRecipe))
                viewModel.onEditRecipeComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    fun onClickToolbar(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                viewModel.onEditRecipe()
                true
            }
            else -> true
        }
    }


//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        val toolbarMenu = R.menu.toolbar_recipe
//        inflater?.inflate(toolbarMenu, menu)
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_edit -> {
//                Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
//                view?.findNavController()
//                    ?.navigate(RecipeFragmentDirections.actionRecipeFragmentToEditRecipeFragment())
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}
