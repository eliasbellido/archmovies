package pe.lumindevs.mobile_coroutines.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.lumindevs.archmovies.entity.database.MovieDao
import pe.lumindevs.archmovies.entity.database.TvDao
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.archmovies.entity.entities.Tv
import pe.lumindevs.archmovies.network.ApiResponse
import pe.lumindevs.archmovies.network.client.TheDiscoverClient
import pe.lumindevs.archmovies.network.message
import timber.log.Timber

class DiscoverRepository constructor(
    private val discoverClient: TheDiscoverClient,
    private val movieDao: MovieDao,
    private val tvDao: TvDao
) : Repository {

    override var isLoading = false

    init {
        Timber.d("Koin Injection DiscoverRepository")
    }

    suspend fun loadMovies(page: Int, error: (String) -> Unit) =
        withContext(Dispatchers.IO) {
            val liveData = MutableLiveData<List<Movie>>()
            var movies = movieDao.getMovieList(page)
            if(movies.isNullOrEmpty()){
                isLoading = true
                discoverClient.fetchDiscoverMovie(page) { response ->
                    isLoading = false
                    when(response) {
                        is ApiResponse.Success -> {
                            response.data?.let { data ->
                                movies = data.results
                                movies.forEach { it.page = page }
                                liveData.postValue(movies)
                                movieDao.insertMovieList(movies)
                            }
                        }
                        is ApiResponse.Failure.Error -> error(response.message())
                        is ApiResponse.Failure.Exception -> error(response.message())
                    }
                }
            }
            liveData.apply { postValue(movies) }
        }

    suspend fun loadTvs(page: Int, error: (String) -> Unit) =
        withContext(Dispatchers.IO) {
            val liveData = MutableLiveData<List<Tv>>()
            var tvs = tvDao.getTvList(page)
            if(tvs.isNullOrEmpty()){
                isLoading = true
                discoverClient.fetchDiscoverTv(page) { response ->
                    isLoading = false
                    when (response) {
                        is ApiResponse.Success -> {
                            response.data?.let { data ->
                                tvs = data.results
                                tvs.forEach { it.page = page }
                                liveData.postValue(tvs)
                                tvDao.insertTv(tvs)
                            }
                        }
                        is ApiResponse.Failure.Error -> error(response.message())
                        is ApiResponse.Failure.Exception -> error(response.message())
                    }
                }
            }
            liveData.postValue(tvs)
            liveData.apply { postValue(tvs) }
        }

    fun getFavouriteMovieList() = movieDao.getFavouriteMovieList()

    fun getFavouriteTvList() = tvDao.getFavouriteTvList()

}