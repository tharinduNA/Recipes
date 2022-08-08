package com.tharindu.recipes.ui.home.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tharindu.recipes.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) = Glide.with(view.context).load(url).error(R.drawable.error).into(view)
