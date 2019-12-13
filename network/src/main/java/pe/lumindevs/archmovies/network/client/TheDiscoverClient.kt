package pe.lumindevs.archmovies.network.client

import pe.lumindevs.archmovies.entity.response.DiscoverMovieResponse
import pe.lumindevs.archmovies.entity.response.DiscoverTvResponse
import pe.lumindevs.archmovies.network.ApiResponse
import pe.lumindevs.archmovies.network.service.TheDiscoverService
import pe.lumindevs.archmovies.network.transform

/** TheDiscoverClient is a UseCase of the [TheDiscoverservice] interface*/
class TheDiscoverClient(private val service: TheDiscoverService){

    fun fetchDiscoverMovie(
        page: Int,
        onResult: (response: ApiResponse<DiscoverMovieResponse>) -> Unit
    ){
        this.service.fetchDiscoverMovie(page).transform(onResult)
    }

    fun fetchDiscoverTv(
        page: Int,
        onResult: (response: ApiResponse<DiscoverTvResponse>) -> Unit
    ){
        this.service.fetchDiscoverTv(page).transform(onResult)
    }
}