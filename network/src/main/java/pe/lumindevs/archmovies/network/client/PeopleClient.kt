package pe.lumindevs.archmovies.network.client

import pe.lumindevs.archmovies.entity.response.PeopleResponse
import pe.lumindevs.archmovies.entity.response.PersonDetail
import pe.lumindevs.archmovies.network.ApiResponse
import pe.lumindevs.archmovies.network.service.PeopleService
import pe.lumindevs.archmovies.network.transform

/** PeopleClient is a useCase of the [PeopleService] interface*/
class PeopleClient(private val service: PeopleService){

    fun fetchPopularPeople(
        page: Int,
        onResult: (response: ApiResponse<PeopleResponse>) -> Unit
    ){
        this.service.fetchPopularPeople(page).transform(onResult)
    }

    fun fetchPersonDetail(
        page: Int,
        onResult: (response: ApiResponse<PersonDetail>) -> Unit
    ){
        this.service.fetchPersonDetail(page).transform(onResult)
    }
}