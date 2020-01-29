package pe.lumindevs.mobile_coroutines.ui.details.tv

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import pe.lumindevs.archmovies.entity.Keyword
import pe.lumindevs.archmovies.entity.Review
import pe.lumindevs.archmovies.entity.Video
import pe.lumindevs.archmovies.entity.entities.Tv
import pe.lumindevs.mobile_coroutines.base.LiveCoroutinesViewModel
import pe.lumindevs.mobile_coroutines.repository.TvRepository
import timber.log.Timber

class TvDetailViewModel constructor(
    private val tvRepository: TvRepository
) : LiveCoroutinesViewModel() {

    private val tvIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val keywordListLiveData: LiveData<List<Keyword>>
    val videoListLiveData: LiveData<List<Video>>
    val reviewListLiveData: LiveData<List<Review>>
    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    private lateinit var tv: Tv
    val favourite = ObservableBoolean()

    init {
        Timber.d("Koin Injection TvDetailViewModel")

        keywordListLiveData = tvIdLiveData.switchMap { id ->
            launchOnViewModelScope{
                tvRepository.loadKeywordList(id) { toastLiveData.postValue(it) }
            }
        }

        videoListLiveData = tvIdLiveData.switchMap { id ->
            launchOnViewModelScope {
                tvRepository.loadVideoList(id) { toastLiveData.postValue(it) }
            }
        }

        reviewListLiveData = tvIdLiveData.switchMap { id ->
            launchOnViewModelScope {
                tvRepository.loadReviewList(id) { toastLiveData.postValue(it) }
            }
        }
    }

    fun postTvId(id: Int){
        tvIdLiveData.postValue(id)
        tv = tvRepository.getTv(id)
        favourite.set(tv.favourite)
    }

    fun getTv() = tv

    fun onClickedFavourite(tv: Tv) =
        favourite.set(tvRepository.onClickFavourite(tv))

}