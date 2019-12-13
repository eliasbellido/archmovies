package pe.lumindevs.archmovies.entity.response

import pe.lumindevs.archmovies.entity.NetworkResponseModel
import pe.lumindevs.archmovies.entity.entities.Tv

data class DiscoverTvResponse(
    val page: Int,
    val results: List<Tv>,
    val total_results: Int,
    val total_pages: Int
): NetworkResponseModel