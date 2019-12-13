package pe.lumindevs.archmovies.entity.response

import pe.lumindevs.archmovies.entity.NetworkResponseModel
import pe.lumindevs.archmovies.entity.entities.Person

data class PeopleResponse(
    val page: Int,
    val result: List<Person>,
    val total_resuultss: Int,
    val total_pages: Int
): NetworkResponseModel