package com.example.cookbook

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.cookbook.databinding.FragmentEditRecipeBinding


class EditRecipeFragment : Fragment() {

    private lateinit var viewModel: EditRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentEditRecipeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit_recipe, container, false)

        viewModel = ViewModelProviders.of(this).get(EditRecipeViewModel::class.java)
        return binding.root
    }

}
