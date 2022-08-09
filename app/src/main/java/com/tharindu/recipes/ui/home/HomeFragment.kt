package com.tharindu.recipes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tharindu.recipes.R
import com.tharindu.recipes.databinding.FragmentHomeBinding
import com.tharindu.recipes.ui.home.adapter.RecipeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(
            inflater, container, false
        ).apply {
            viewModel = this@HomeFragment.viewModel
            lifecycleOwner = this@HomeFragment
        }
        subscribeForLiveData()
        return binding.root

    }

    private fun subscribeForLiveData() {
        viewModel.data.observe(viewLifecycleOwner) {
            it?.let {
                val recipeAdapter = RecipeAdapter(it) {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                    findNavController().navigate(action)
                }
                binding.recyclerView.adapter = recipeAdapter
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                showError()
            }
        }
    }

    private fun showError() = Toast.makeText(
        requireContext(),
        R.string.something_went_wrong,
        Toast.LENGTH_LONG
    )

}