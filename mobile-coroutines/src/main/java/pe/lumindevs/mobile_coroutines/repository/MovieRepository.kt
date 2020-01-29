package pe.lumindevs.mobile_coroutines.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.lumindevs.archmovies.entity.Keyword
import pe.lumindevs.archmovies.entity.Review
import pe.lumindevs.archmovies.entity.Video
import pe.lumindevs.archmovies.entity.database.MovieDao
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.archmovies.network.ApiResponse
import pe.lumindevs.archmovies.network.client.MovieClient
import pe.lumindevs.archmovies.network.message
import timber.log.Timber

class MovieRepository constructor(
    private val movieClient: MovieClient,
    private val movieDao: MovieDao
) : Repository {

    override var isLoading = false

    init {
        Timber.d("Koin Injection MovieRepository")
    }

    suspend fun loadKeywordList(id: Int, error: (String) -> Unit) =
        withContext(Dispatchers.IO){
            val livedata = MutableLiveData<List<Keyword>>()
            val movie = movieDao.getMovie(id)
            var keywords = movie.keywords
            if(keywords.isNullOrEmpty()){
                isLoading = true
                movieClient.fetchKeywords(id) { response ->
                    isLoading = false
                    when (response) {
                        is ApiResponse.Success -> {
                            response.data?.let { data ->
                                keywords = data.keywords
                                movie.keywords = keywords
                                livedata.postValue(keywords)
                                movieDao.updateMovie(movie)
                            }
                        }
                        is ApiResponse.Failure.Error -> error(response.message())
                        is ApiResponse.Failure.Exception -> error(response.message())
                    }
                }
            }
            livedata.apply { postValue(keywords) }
        }

    suspend fun loadVideoList(id: Int, error: (String) -> Unit) =
        withContext(Dispatchers.IO){
            val liveData = MutableLiveData<List<Video>>()
            val movie = movieDao.getMovie(id)
            var videos = movie.videos
            if(videos.isNullOrEmpty()){
                isLoading = true
                movieClient.fetchVideos(id) { response ->
                    isLoading = false
                    when (response) {
                        is ApiResponse.Success -> {
                            response.data?.let { data ->
                                videos = data.results
                                movie.videos = videos
                                liveData.postValue(videos)
                                movieDao.updateMovie(movie)
                            }
                        }
                        is ApiResponse.Failure.Error -> error(response.message())
                        is ApiResponse.Failure.Exception -> error(response.message())
                    }
                }
            }
            liveData.apply { postValue(videos) }
        }

    suspend fun loadReviewList(id: Int, error: (String) -> Unit) =
        withContext(Dispatchers.IO){
            val liveData = MutableLiveData<List<Review>>()
            val movie = movieDao.getMovie(id)
            var reviews = movie.reviews
            if (reviews.isNullOrEmpty()) {
                isLoading = true
                movieClient.fetchReviews(id) { response ->
                    isLoading = false
                    when (response) {
                        is ApiResponse.Success -> {
                            response.data?.let { data ->
                                reviews = data.results
                                movie.reviews = reviews
                                liveData.postValue(reviews)
                                movieDao.updateMovie(movie)
                            }
                        }
                        is ApiResponse.Failure.Error -> error(response.message())
                        is ApiResponse.Failure.Exception -> error(response.message())
                    }
                }
            }
            liveData.apply { postValue(reviews) }
        }

    fun getMovie(id: Int) = movieDao.getMovie(id)

    fun onClickFavourite(movie: Movie) : Boolean {
        movie.favourite = !movie.favourite
        movieDao.updateMovie(movie)
        return movie.favourite
    }

}