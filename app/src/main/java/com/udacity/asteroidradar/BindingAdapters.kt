package com.udacity.asteroidradar


import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.main.MainAdapter


@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
        imageView.contentDescription = "this is Hazardous "
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
        imageView.contentDescription="this isn't Hazardous"
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
        imageView.contentDescription="this is Hazardous image"
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
        imageView.contentDescription="this isn't Hazardous image"
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("imageUrl")
fun binImage(imageView: ImageView,imgUrl:String?){
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
           Picasso.with(imageView.context)
               .load(imgUri)
               .error(R.drawable.ic_launcher_foreground)
               .into(imageView)
        println(imgUri)
    }
//
//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView, data: ArrayList<Asteroid>?) {
//        val adapter = recyclerView.adapter as MainAdapter
//        adapter.submitList(data)
//    }
}
