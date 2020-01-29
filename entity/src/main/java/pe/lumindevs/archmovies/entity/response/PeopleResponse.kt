package pe.lumindevs.archmovies.entity.response

import pe.lumindevs.archmovies.entity.NetworkResponseModel
import pe.lumindevs.archmovies.entity.entities.Person

data class PeopleResponse(
    val page: Int,
    val results: List<Person>,
    val total_resuults: Int,
    val total_pages: Int
): NetworkResponseModel