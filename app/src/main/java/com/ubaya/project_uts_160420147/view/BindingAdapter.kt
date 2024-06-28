package com.ubaya.project_uts_160420147.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

//@BindingAdapter("android:imageUrl")
//fun loadPhotoURL(imageView: ImageView, url:String) {
//    val picasso = Picasso.Builder(imageView.context)
//    picasso.listener { picasso, uri, exception ->
//        exception.printStackTrace()
//    }
//    picasso.build().load(url).into(imageView)
//}
@BindingAdapter("android:imageUrl")
fun loadPhotoURL(imageView: ImageView, url:String) {
    val picasso = Picasso.Builder(imageView.context)
    picasso.listener { picasso, uri, exception ->
        exception.printStackTrace()
    }
    picasso.build().load(url).into(imageView)
}

//@BindingAdapter("android:imageUrl")
//fun loadPhotoURL(imageView: ImageView, url: String?) {
//    if (!url.isNullOrEmpty()) {
//        val picasso = Picasso.Builder(imageView.context).listener { picasso, uri, exception ->
//            exception.printStackTrace()
//        }.build()
//
//        picasso.load(url).into(imageView)
//    } else {
//        // Optionally handle the case where the URL is null or empty
//        imageView.setImageResource(R.drawable.placeholder_image) // Replace with a valid placeholder image resource
//    }
//}