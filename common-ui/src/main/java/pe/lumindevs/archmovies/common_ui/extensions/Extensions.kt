package pe.lumindevs.archmovies.common_ui.extensions

import android.os.Build

/** returns whether device sdk version is over than 21 or not*/
fun checkIsMaterialVersion() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

