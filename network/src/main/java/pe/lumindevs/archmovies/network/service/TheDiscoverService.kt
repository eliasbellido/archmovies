package pe.lumindevs.archmovies.network.service

import pe.lumindevs.archmovies.entity.response.DiscoverMovieResponse
import pe.lumindevs.archmovies.entity.response.DiscoverTvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheDiscoverService {

    @GET("/3/discover/movie?language=es&sort_by=popularity.desc")
    fun fetchDiscoverMovie(@Query("page") page: Int): Call<DiscoverMovieResponse>

    @GET("/3/discover/tv?language=es&sort_by=popularity.desc")
    fun fetchDiscoverTv(@Query("page") page: Int): Call<DiscoverTvResponse>
}