package pe.lumindevs.archmovies.common_ui.bindings

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.whatif.whatIfNotNullOrEmpty
import pe.lumindevs.archmovies.common_ui.PosterPath
import pe.lumindevs.archmovies.common_ui.adapters.*
import pe.lumindevs.archmovies.common_ui.extensions.visible
import pe.lumindevs.archmovies.entity.Review
import pe.lumindevs.archmovies.entity.Video
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.archmovies.entity.entities.Person
import pe.lumindevs.archmovies.entity.entities.Tv

@BindingAdapter("adapterMovieList")
fun bindAdapterMovieList(view: RecyclerView, movies: List<Movie>?){
    movies.whatIfNotNullOrEmpty {
        val adapter = view.adapter as? MovieListAdapter
        adapter?.addMovieList(it)
    }
}

@BindingAdapter("adapterTvList")
fun bindAdapterTvList(view: RecyclerView, tvs: List<Tv>?){
    tvs.whatIfNotNullOrEmpty {
        val adapter = view.adapter as? TvListAdapter
        adapter?.addTvList(it)
    }
}

@BindingAdapter("adapterPersonList")
fun bindAdapterPersonList(view: RecyclerView, tvs: List<Person>?){
    tvs.whatIfNotNullOrEmpty {
        val adapter = view.adapter as? PeopleAdapter
        adapter?.addPerson(it)
    }
}

@BindingAdapter("adapterVideoList")
fun bindAdapterVideoList(recyclerView: RecyclerView, videos: List<Video>?){
    videos.whatIfNotNullOrEmpty {
        val adapter = VideoListAdapter()
        adapter.addVideoList(it)
        recyclerView.adapter = adapter
        recyclerView.visible()
    }
}

@BindingAdapter("adapterReviewList")
fun bindAdapterReviewList(recyclerView: RecyclerView, reviews: List<Review>?){
    reviews.whatIfNotNullOrEmpty {
        val adapter = ReviewListAdapter()
        adapter.addReviewList(it)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setHasFixedSize(true)
        recyclerView.visible()
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