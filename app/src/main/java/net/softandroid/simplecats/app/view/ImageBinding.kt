package net.softandroid.teststproject.app.view

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    if (imageUrl == null) {
        imageView.setImageDrawable(null)
        return
    }
    Glide.with(imageView).let {
        if (imageUrl.startsWith("content://")) {
            it.load(Uri.parse(imageUrl))
        } else {
            it.load(imageUrl)
        }
    }.into(imageView)
}