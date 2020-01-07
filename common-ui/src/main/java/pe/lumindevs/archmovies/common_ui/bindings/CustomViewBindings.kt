package pe.lumindevs.archmovies.common_ui.bindings

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ms.square.android.expandabletextview.ExpandableTextView
import pe.lumindevs.archmovies.common_ui.R
import pe.lumindevs.archmovies.common_ui.extensions.simpleToolbarWithHome

@BindingAdapter("bindExpandableTextView")
fun bindExpandableTextView(expandableTextView: ExpandableTextView, text: String?){
    expandableTextView.text = text
}

@BindingAdapter("bindFavourite")
fun bindFavourite(imageView: ImageView, favourite: Boolean){
    if(favourite)
        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, R.drawable.ic_heart))
    else
        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, R.drawable.ic_heart_border))
}

@BindingAdapter("observeFavourite")
fun observeFavourite(imageView: ImageView, favourite: Boolean){
    bindFavourite(imageView, favourite)
}

@BindingAdapter("simpleToolbarWithHome", "simpleToolbarTitle")
fun simpleToolbarWithHome(toolbar: Toolbar, activity: AppCompatActivity, title: String){
    activity.simpleToolbarWithHome(toolbar, title)
}