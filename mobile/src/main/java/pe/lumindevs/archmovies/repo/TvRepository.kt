package pe.lumindevs.archmovies.repo

import androidx.lifecycle.MutableLiveData
import pe.lumindevs.archmovies.entity.Keyword
import pe.lumindevs.archmovies.entity.Review
import pe.lumindevs.archmovies.entity.Video
import pe.lumindevs.archmovies.entity.database.TvDao
import pe.lumindevs.archmovies.entity.entities.Tv
import pe.lumindevs.archmovies.network.ApiResponse
import pe.lumindevs.archmovies.network.client.TvClient
import pe.lumindevs.archmovies.network.message
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvRepository @Inject constructor(
    private val tvClient: TvClient,
    private val tvDao: TvDao
) : Repository {

    override var isLoading = false

    init {
        Timber.d("Injection TvRepository")
    }

    fun loadKeywordList(id: Int, error: (String) -> Unit): MutableLiveData<List<Keyword>>{
        val liveData = MutableLiveData<List<Keyword>>()
        val tv = tvDao.getTv(id)
        var keywords = tv.keywords
        if(keywords.isNullOrEmpty()){
            isLoading = true
            tvClient.fetchKeywords(id){ response ->
                isLoading = false
                when(response){
                    is ApiResponse.Success -> {
                        response.data?.let { data ->
                            keywords = data.keywords
                            tv.keywords = keywords
                            liveData.postValue(keywords)
                            tvDao.updateTv(tv)
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

    fun loadVideoList(id: Int, error: (String) -> Unit) : MutableLiveData<List<Video>> {
        val liveData = MutableLiveData<List<Video>>()
        val tv = tvDao.getTv(id)
        var videos = tv.videos
        if(videos.isNullOrEmpty()){
            isLoading = true
            tvClient.fetchVideos(id) { response ->
                isLoading = false
                when (response) {
                    is ApiResponse.Success -> {
                        response.data?.let{ data ->
                            videos = data.results
                            tv.videos = videos
                            liveData.postValue(videos)
                            tvDao.updateTv(tv)
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
        val tv = tvDao.getTv(id)
        var reviews = tv.reviews
        if(reviews.isNullOrEmpty()){
            isLoading = true
            tvClient.fetchReviews(id) { response ->
                isLoading = false
                when(response){
                    is ApiResponse.Success -> {
                        response.data?.let { data ->
                            reviews = data.results
                            tv.reviews = reviews
                            liveData.postValue(reviews)
                            tvDao.updateTv(tv)
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

    fun getTv(id: Int) = tvDao.getTv(id)

    fun onClickFavourite(tv: Tv): Boolean {
        tv.favourite= !tv.favourite
        tvDao.updateTv(tv)
        return tv.favourite
    }

}