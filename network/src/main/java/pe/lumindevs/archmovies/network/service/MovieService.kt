package pe.lumindevs.archmovies.network.service

import pe.lumindevs.archmovies.entity.response.KeywordListResponse
import pe.lumindevs.archmovies.entity.response.ReviewListResponse
import pe.lumindevs.archmovies.entity.response.VideoListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("/3/movie/{movie_id}/keywords")
    fun fetchKeywords(@Path("movie_id") id: Int): Call<KeywordListResponse>

    @GET("/3/movie/{movie_id}/videos")
    fun fetchVideos(@Path("movie_id") id: Int): Call<VideoListResponse>

    @GET("/3/movie/{movie_id}/reviews")
    fun fetchReviews(@Path("movie_id") id: Int): Call<ReviewListResponse>
}