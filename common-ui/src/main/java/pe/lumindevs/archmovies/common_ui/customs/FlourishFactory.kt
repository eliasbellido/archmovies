package pe.lumindevs.archmovies.common_ui.customs

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.skydoves.flourish.Flourish
import com.skydoves.flourish.FlourishAnimation
import com.skydoves.flourish.FlourishOrientation
import com.skydoves.flourish.createFlourish

/**
 * FlourishFactory creates an instance of the [Flourish].
 * */
object FlourishFactory{

    /** creates an instance of the [Flourish]*/
    fun create(parentView: ViewGroup, @LayoutRes resId: Int): Flourish{
        return createFlourish(parentView){
            setFlourishLayout(resId)
            setFlourishAnimation(FlourishAnimation.BOUNCE)
            setFlourishOrientation(FlourishOrientation.TOP_RIGHT)
        }
    }
}