package pe.lumindevs.archmovies.network.client

import pe.lumindevs.archmovies.entity.response.KeywordListResponse
import pe.lumindevs.archmovies.entity.response.ReviewListResponse
import pe.lumindevs.archmovies.entity.response.VideoListResponse
import pe.lumindevs.archmovies.network.ApiResponse
import pe.lumindevs.archmovies.network.service.MovieService
import pe.lumindevs.archmovies.network.transform

/** MovieClient is a UseCase of the [MovieService] interface.*/
class MovieClient(private val service: MovieService){

    fun fetchKeywords(
        id: Int,
        onResult: (response: ApiResponse<KeywordListResponse>) -> Unit
    ){
        this.service.fetchKeywords(id).transform(onResult)
    }

    fun fetchVideos(
        id: Int,
        onResult: (response: ApiResponse<VideoListResponse>) -> Unit
    ){
        this.service.fetchVideos(id).transform(onResult)
    }

    fun fetchReviews(
        id: Int,
        onResult: (response: ApiResponse<ReviewListResponse>) -> Unit
    ){
        this.service.fetchReviews(id).transform(onResult)
    }
}