package pe.lumindevs.archmovies.common_ui.bindings

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.skydoves.whatif.whatIfNotNull
import org.threeten.bp.LocalDate
import pe.lumindevs.archmovies.common_ui.PosterPath
import pe.lumindevs.archmovies.common_ui.extensions.gone
import pe.lumindevs.archmovies.common_ui.extensions.visible
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.archmovies.entity.entities.Tv

@BindingAdapter("bindingPostUrl")
fun bindingPostUrl(imageView: ImageView, path: String?){
    path?.let{
        Glide.with(imageView.context)
            .load(PosterPath.getPosterPath(it))
            .apply(RequestOptions().circleCrop())
            .into(imageView)
    }
}

@BindingAdapter("bindingPalettePostUrl", "palette")
fun bindingPalettePostUrl(imageView: ImageView, path: String?, palette: View){
    path?.let {
        Glide.with(imageView.context)
            .load(PosterPath.getPosterPath(it))
            .listener(GlidePalette.with(PosterPath.getPosterPath(it))
                .use(BitmapPalette.Profile.VIBRANT)
                .intoBackground(palette)
                .crossfade(true))
            .into(imageView)
    }
}

@BindingAdapter("bindingRibbonOnMovie")
fun bindingRibbonOnMovie(view: View, movie: Movie){
    movie.release_date.whatIfNotNull {
        val now = LocalDate.now()
        val releaseDate = LocalDate.parse(it).plusMonths(1)
        if(releaseDate.isAfter(now)){
            view.visible()
        }else{
            view.gone()
        }
    }
}

@BindingAdapter("bindingRibbonOnTv")
fun bindingRibbonOnTv(view: View, tv: Tv){
    if(tv.vote_average / 2 >= 3.5){
        view.visible()
    }else{
        view.gone()
    }
}

@BindingAdapter("bindingPaletteYoutubeUrl", "palette")
fun bindingPaletteYoutubeUrl(imageView: ImageView, path: String?, palette: View){
    path?.let {
        Glide.with(imageView.context)
            .load(PosterPath.getYoutubeThumbnailPath(it))
            .listener(GlidePalette.with(PosterPath.getYoutubeThumbnailPath(it))
                .use(BitmapPalette.Profile.VIBRANT)
                .intoBackground(palette)
                .crossfade(true))
            .into(imageView)
    }
}

