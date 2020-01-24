package pe.lumindevs.archmovies.ui.details.tv

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import pe.lumindevs.archmovies.entity.Keyword
import pe.lumindevs.archmovies.entity.Review
import pe.lumindevs.archmovies.entity.Video
import pe.lumindevs.archmovies.entity.entities.Tv
import pe.lumindevs.archmovies.repo.TvRepository
import timber.log.Timber
import javax.inject.Inject

class TvDetailViewModel @Inject constructor( private val tvRepository: TvRepository)
    : ViewModel() {

    private val tvIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val keywordListLiveData: LiveData<List<Keyword>>
    val videoListLiveData: LiveData<List<Video>>
    val reviewListLiveData: LiveData<List<Review>>
    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    private lateinit var tv: Tv
    val favourite = ObservableBoolean()

    init {
        Timber.d("Injection TvDetailViewModel")

        keywordListLiveData = tvIdLiveData.switchMap { id ->
            tvRepository.loadKeywordList(id) { toastLiveData.postValue(it) }
        }

        videoListLiveData = tvIdLiveData.switchMap { id ->
            tvRepository.loadVideoList(id) { toastLiveData.postValue(it) }
        }

        reviewListLiveData = tvIdLiveData.switchMap { id ->
            tvRepository.loadReviewsList(id) { toastLiveData.postValue(it) }
        }
    }

    fun postTvId(id: Int) {
        tvIdLiveData.postValue(id)
        tv = tvRepository.getTv(id)
        favourite.set(tv.favourite)
    }

    fun getTv() = tv

    fun onClickedFavourite(tv: Tv) =
        favourite.set(tvRepository.onClickFavourite(tv))
}