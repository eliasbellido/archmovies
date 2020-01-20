package pe.lumindevs.archmovies.ui.details.movie

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import pe.lumindevs.archmovies.entity.Keyword
import pe.lumindevs.archmovies.entity.Review
import pe.lumindevs.archmovies.entity.Video
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.archmovies.repo.MovieRepository
import timber.log.Timber
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository) : ViewModel() {

    private val movieIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val keyboardListLiveData: LiveData<List<Keyword>>
    val videoListLiveData: LiveData<List<Video>>
    val reviewListLiveData: LiveData<List<Review>>
    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    private lateinit var movie: Movie
    val favourite = ObservableBoolean()

    init {
        Timber.d("Injection MovieDetailViewModel")

        keyboardListLiveData = movieIdLiveData.switchMap { id ->
            movieRepository.loadKeywordList(id) { toastLiveData.postValue(it) }
        }

        videoListLiveData = movieIdLiveData.switchMap { id ->
            movieRepository.loadVideoList(id) { toastLiveData.postValue(it) }
        }

        reviewListLiveData = movieIdLiveData.switchMap { id ->
            movieRepository.loadReviewsList(id) { toastLiveData.postValue(it) }
        }
    }

    fun postMovieId(id: Int){
        movieIdLiveData.postValue(id)
        movie = movieRepository.getMovie(id)
        favourite.set(movie.favourite)
    }

    fun getMovie() = movie

    fun onClickedFavourite(movie: Movie) =
        favourite.set(movieRepository.onClickFavourite(movie))
}