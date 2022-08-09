package com.tharindu.recipes.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tharindu.recipes.R
import com.tharindu.recipes.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(
            inflater, container, false
        ).apply {
            viewModel = this@DetailFragment.viewModel
            lifecycleOwner = this@DetailFragment
        }

        subscribeForEvents()
        viewModel.updateRecipe(args.recipeDomain)

        return binding.root

    }

    private fun subscribeForEvents() {
        viewModel.onAppBackPressed.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_DetailFragment_to_HomeFragment)
        }

        viewModel.recipeDomain.observe(viewLifecycleOwner) {
            viewModel.prepareData()
        }
    }

}