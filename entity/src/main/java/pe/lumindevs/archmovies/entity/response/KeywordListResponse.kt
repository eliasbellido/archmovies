package pe.lumindevs.archmovies.entity.response

import pe.lumindevs.archmovies.entity.Keyword
import pe.lumindevs.archmovies.entity.NetworkResponseModel

data class KeywordListResponse(
    val id: Int,
    val keywords: List<Keyword>
): NetworkResponseModel