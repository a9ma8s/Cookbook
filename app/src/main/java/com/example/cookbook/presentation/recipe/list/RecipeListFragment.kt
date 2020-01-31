package com.example.cookbook.presentation.recipe.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cookbook.R
import com.example.cookbook.data.RecipeDatabase
import com.example.cookbook.databinding.FragmentRecipeListBinding


class RecipeListFragment : Fragment() {

    private lateinit var viewModelFactory: RecipeListViewModelFactory
    private lateinit var viewModel: RecipeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRecipeListBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_recipe_list, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = RecipeDatabase.getInstance(application).recipeDatabaseDao

        viewModelFactory = RecipeListViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RecipeListViewModel::class.java)

        binding.viewModel = viewModel

        val adapter =
            RecipeAdapter(RecipeAdapter.OnClickListener {
                viewModel.onRecipeClicked(it)
            })
        binding.listRecipe.adapter = adapter

        binding.toolbarListRecipe.setOnMenuItemClickListener { onClickToolbar(it) }

        binding.lifecycleOwner = this

        viewModel.recipes.observe(viewLifecycleOwner, Observer {
            it?.let { adapter.submitList(it) }
        })

        viewModel.navigateToSelectedRecipe.observe(viewLifecycleOwner, Observer { recipe ->
            recipe?.let {
                this.findNavController().navigate(RecipeListFragmentDirections.actionRecipeListFragmentToRecipeFragment(recipe))
                viewModel.onRecipeNavigated()
            }
        })

        viewModel.navigateToAddRecipe.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(RecipeListFragmentDirections.actionRecipeListFragmentToAddRecipeFragment())
                viewModel.onAddRecipeNavigated()
            }
        })

        val manager = GridLayoutManager(activity, 2)
        binding.listRecipe.layoutManager = manager

        return binding.root
    }

    private fun onClickToolbar(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                viewModel.onClear()
                true
            }
            else -> true
        }
    }

}
