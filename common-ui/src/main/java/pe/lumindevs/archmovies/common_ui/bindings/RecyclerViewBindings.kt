package pe.lumindevs.archmovies.common_ui.bindings

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.whatif.whatIfNotNullOrEmpty
import pe.lumindevs.archmovies.common_ui.PosterPath
import pe.lumindevs.archmovies.common_ui.adapters.MovieListAdapter
import pe.lumindevs.archmovies.entity.Video
import pe.lumindevs.archmovies.entity.entities.Movie

@BindingAdapter("adapterMovieList")
fun bindAdapterMovieList(view: RecyclerView, movies: List<Movie>?){
    movies.whatIfNotNullOrEmpty {
        val adapter = view.adapter as? MovieListAdapter
        adapter?.addMovieList(it)
    }
}

@BindingAdapter("onVideoItemClick")
fun onVideoItemClick(view: View, video: Video){
    view.setOnClickListener {
        val playVideoIntent = Intent(
            Intent.ACTION_VIEW, Uri.parse(PosterPath.getYoutubeVideoPath(video.key)))
        view.context.startActivity(playVideoIntent)
    }
}