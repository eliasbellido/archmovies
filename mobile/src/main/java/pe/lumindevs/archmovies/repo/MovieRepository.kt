package pe.lumindevs.archmovies.repo

import androidx.lifecycle.MutableLiveData
import pe.lumindevs.archmovies.entity.Keyword
import pe.lumindevs.archmovies.entity.Review
import pe.lumindevs.archmovies.entity.Video
import pe.lumindevs.archmovies.entity.database.MovieDao
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.archmovies.network.ApiResponse
import pe.lumindevs.archmovies.network.client.MovieClient
import pe.lumindevs.archmovies.network.message
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieClient: MovieClient,
    private val movieDao: MovieDao
): Repository{

    override var isLoading = false

    init {
        Timber.d("Injection MovieRepository")
    }

    fun loadKeywordList(id: Int, error: (String) -> Unit): MutableLiveData<List<Keyword>>{
        val liveData = MutableLiveData<List<Keyword>>()
        val movie = movieDao.getMovie(id)
        var keywords = movie.keywords
        if(keywords.isNullOrEmpty()){
            isLoading = true
            movieClient.fetchKeywords(id){
                response ->
                isLoading = false
                when(response){
                    is ApiResponse.Success -> {
                        response.data?.let { data ->
                            keywords = data.keywords
                            movie.keywords = keywords
                            liveData.postValue(keywords)
                            movieDao.updateMovie(movie)
                        }
                    }
                    is ApiResponse.Failure.Error -> error(response.message())
                    is ApiResponse.Failure.Exception -> error(response.message())
                }
            }
        }
        liveData.postValue(keywords)
        return liveData
    }

    fun loadVideoList(id: Int, error: (String) -> Unit): MutableLiveData<List<Video>>{
        val liveData = MutableLiveData<List<Video>>()
        val movie = movieDao.getMovie(id)
        var videos = movie.videos

        if(videos.isNullOrEmpty()){
            isLoading = true
            movieClient.fetchVideos(id) { response ->
                isLoading = false
                when(response){
                    is ApiResponse.Success -> {
                        response.data?.let {  data ->
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
        liveData.postValue(videos)
        return liveData
    }

    fun loadReviewsList(id: Int, error: (String) -> Unit): MutableLiveData<List<Review>>{
        val liveData = MutableLiveData<List<Review>>()
        val movie = movieDao.getMovie(id)
        var reviews = movie.reviews
        if(reviews.isNullOrEmpty()){
            isLoading = true
            movieClient.fetchReviews(id) { response ->
                isLoading = false
                when(response){
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
        liveData.postValue(reviews)
        return liveData
    }

    fun getMovie(id: Int) = movieDao.getMovie(id)

    fun onClickFavourite(movie: Movie): Boolean {
        movie.favourite = !movie.favourite
        movieDao.updateMovie(movie)
        return movie.favourite
    }
}