package pe.lumindevs.archmovies.common_ui.extensions

import android.content.Context
import android.widget.Toast

/** shows a toast message*/
fun Context.shortToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()