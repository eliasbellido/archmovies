package pe.lumindevs.archmovies.network.service

import pe.lumindevs.archmovies.entity.response.PeopleResponse
import pe.lumindevs.archmovies.entity.response.PersonDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleService {

    @GET("/3/person/popular?language=es")
    fun fetchPopularPeople(@Query("page") page: Int): Call<PeopleResponse>

    @GET("/3/person/{person_id}")
    fun fetchPersonDetail(@Path("person_id") id: Int): Call<PersonDetail>
}