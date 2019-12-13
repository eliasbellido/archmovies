package pe.lumindevs.archmovies.entity.response

import pe.lumindevs.archmovies.entity.NetworkResponseModel
import pe.lumindevs.archmovies.entity.Video

data class VideoListResponse(
    val id: Int,
    val results: List<Video>
): NetworkResponseModel