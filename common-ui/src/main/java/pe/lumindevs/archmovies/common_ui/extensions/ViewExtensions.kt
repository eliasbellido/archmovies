package pe.lumindevs.archmovies.common_ui.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewAnimationUtils
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import pe.lumindevs.archmovies.common_ui.R

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

/** request glide listener for delaying circular revealed at center of the poster image*/
fun View.requestGlideListener(): RequestListener<Drawable>{
    return object: RequestListener<Drawable>{
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ) = false

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            circularRevealedAtCenter()
            return false
        }
    }
}

/** circular revealed at center of a view. */
fun View.circularRevealedAtCenter(){
    val view = this
    val cx = (left + right) / 2
    val cy = (top+ bottom) / 2
    val finalRadius = view.width.coerceAtLeast(height)

    if(checkIsMaterialVersion() && isAttachedToWindow){
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius.toFloat())
        visible()
        setBackgroundColor(ContextCompat.getColor(view.context, R.color.background))
        anim.duration = 550
        anim.start()

    }
}