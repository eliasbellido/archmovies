package pe.lumindevs.archmovies.common_ui.bindings

import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.BindingAdapter

@BindingAdapter("onBackPressed")
fun onBackPressed(view: View, activity: ComponentActivity){
    view.setOnClickListener { activity.onBackPressed() }
}