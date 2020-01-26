package com.example.cookbook

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageResource")
fun bindImage(imageView: ImageView, imageId: Int) {
    if (imageId != 0) imageView.setImageResource(imageId)
}