package pe.lumindevs.archmovies.common_ui.bindings

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullOrEmpty
import pe.lumindevs.archmovies.common_ui.PosterPath
import pe.lumindevs.archmovies.common_ui.R
import pe.lumindevs.archmovies.common_ui.extensions.requestGlideListener
import pe.lumindevs.archmovies.common_ui.extensions.visible
import pe.lumindevs.archmovies.entity.Keyword
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.archmovies.entity.entities.Person
import pe.lumindevs.archmovies.entity.entities.Tv
import pe.lumindevs.archmovies.entity.response.PersonDetail

@BindingAdapter("visibilityByModel")
fun visibilityByModel(view: View, anyList: List<Any>?){
    anyList.whatIfNotNullOrEmpty {
        view.visible()
    }
}

@BindingAdapter("mapKeywordList")
fun bindMapKeywordList(chipGroup: ChipGroup, keywords: List<Keyword>?){
    keywords.whatIfNotNullOrEmpty {
        chipGroup.visible()
        for(keyword in it){
            val chip = Chip(chipGroup.context)
            chip.text = keyword.name
            chip.isCheckable = false
            chip.setTextAppearanceResource(R.style.ChipTextStyle)
            chip.setChipBackgroundColorResource(R.color.colorPrimary)
            chipGroup.addView(chip)
        }
    }
}

@BindingAdapter("mapNameTagList")
fun bindTags(chipGroup: ChipGroup, personDetail: PersonDetail?){
    personDetail?.also_known_as?.whatIfNotNull {
        chipGroup.visible()
        for(nameTag in it){
            val chip = Chip(chipGroup.context)
            chip.text = nameTag
            chip.isCheckable = false
            chip.setTextAppearanceResource(R.style.ChipTextStyle)
            chip.setChipBackgroundColorResource(R.color.colorPrimary)
            chipGroup.addView(chip)
        }
    }
}

@BindingAdapter("bindReleaseDate")
fun bindReleaseDate(view: TextView, movie: Movie){
    view.text = "Relase Date : ${movie.release_date}"
}

@BindingAdapter("bindAirDate")
fun bindAirDate(view: TextView, tv: Tv){
    view.text = "First Air Date: ${tv.first_air_date}"
}

@BindingAdapter("biography")
fun bindBiography(view: TextView, personDetail: PersonDetail?){
    view.text = personDetail?.biography
}

@BindingAdapter("bindBackDrop")
fun bindBackDrop(view: ImageView, movie: Movie){
    bindBackDrop(view, movie.backdrop_path, movie.poster_path)
}

@BindingAdapter("bindBackDrop")
fun bindBackDrop(view: ImageView, tv: Tv){
    bindBackDrop(view, tv.backdrop_path, tv.poster_path)
}

@BindingAdapter("bindBackDrop")
fun bindBackdrop(view: ImageView, person: Person){
    person.profile_path.whatIfNotNull {
        Glide.with(view.context).load(PosterPath.getBackdropPath(it))
            .apply(RequestOptions().circleCrop())
            .into(view)
    }
}


//
private fun bindBackDrop(view: ImageView, path: String?, posterPath: String?){

    path.whatIfNotNull (
        whatIf = {
            Glide.with(view.context).load(PosterPath.getBackdropPath(it))
                .listener(view.requestGlideListener())
                .into(view)

        },
        whatIfNot = {
            Glide.with(view.context).load(PosterPath.getBackdropPath(posterPath))
                .listener(view.requestGlideListener())
        }
    )
}