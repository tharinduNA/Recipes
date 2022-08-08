package com.tharindu.recipes.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tharindu.recipes.R
import com.tharindu.recipes.databinding.RecipeItemBinding
import com.tharindu.recipes.domain.entity.RecipeDomain

class RecipeAdapter(
    private var recipeList: List<RecipeDomain>,
    private val itemClickListener: (RecipeDomain) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private lateinit var binding: RecipeItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecipeViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recipe_item, parent, false
        )
        return RecipeViewHolder(binding.root)
    }

    override fun getItemCount() = recipeList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        binding.item = recipeList[position]
        holder.bind(recipeList[position], itemClickListener)
    }

    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(recipeDomain: RecipeDomain, onSelect: (RecipeDomain) -> Unit) {

            binding.root.setOnClickListener {
                onSelect(recipeDomain)
            }
        }
    }

}