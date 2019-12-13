package pe.lumindevs.archmovies.entity.response

import pe.lumindevs.archmovies.entity.NetworkResponseModel
import pe.lumindevs.archmovies.entity.Review

class ReviewListResponse (
    val id: Int,
    val page: Int,
    val results: List<Review>,
    val total_pages: Int,
    val total_results: Int
): NetworkResponseModel