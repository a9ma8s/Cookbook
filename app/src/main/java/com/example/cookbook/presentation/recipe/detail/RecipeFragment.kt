package com.example.cookbook.presentation.recipe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.cookbook.R
import com.example.cookbook.data.RecipeDatabase
import com.example.cookbook.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private lateinit var viewModelFactory: RecipeViewModelFactory
    private lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRecipeBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_recipe, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = RecipeDatabase.getInstance(application).recipeDatabaseDao

        viewModelFactory = RecipeViewModelFactory(RecipeFragmentArgs.fromBundle(arguments!!).recipeKey, dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RecipeViewModel::class.java)

        binding.viewModel = viewModel

        val navController = findNavController()
        binding.toolbarRecipe.setupWithNavController(navController)
        binding.toolbarRecipe.setOnMenuItemClickListener { onClickToolbar(it) }

        binding.lifecycleOwner = this

        viewModel.editRecipe.observe(viewLifecycleOwner, Observer { editRecipe ->
            if (editRecipe) {
                findNavController().navigate(
                    RecipeFragmentDirections.actionRecipeFragmentToEditRecipeFragment(
                        viewModel.recipeKey
                    )
                )
                viewModel.onEditRecipeComplete()
            }
        })

        viewModel.navigateToRecipeList.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(RecipeFragmentDirections.actionRecipeFragmentToRecipeListFragment())
                viewModel.onNavigateToRecipeListComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun onClickToolbar(item: MenuItem): Boolean {
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
