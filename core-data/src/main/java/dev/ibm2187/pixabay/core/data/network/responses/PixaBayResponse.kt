package dev.ibm2187.pixabay.core.data.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PixaBayResponse(
    @Json(name = "hits") val hits: List<Hit>,
    @Json(name = "total") val total: Int,
    @Json(name = "totalHits") val totalHits: Int
)