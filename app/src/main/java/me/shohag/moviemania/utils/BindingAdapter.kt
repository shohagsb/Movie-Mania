package me.shohag.moviemania.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import me.shohag.moviemania.R

@BindingAdapter("loadImg")
fun loadImg(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.loading_anim)
            .error(R.drawable.film)
            .into(imageView)
    }

}