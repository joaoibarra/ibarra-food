package com.joaoibarra.food.ui.common

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joaoibarra.food.R


@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>?) {
    this.run {
        this.adapter = adapter
    }
}

@BindingAdapter("app:favIcon")
fun favIcon(v: AppCompatImageView, isFavorite: Boolean) {
    val drawableRes: Int =
        if (isFavorite) R.drawable.ic_favorite_black_48dp else R.drawable.ic_favorite_border_black_48dp
    v.setImageResource(drawableRes)
}