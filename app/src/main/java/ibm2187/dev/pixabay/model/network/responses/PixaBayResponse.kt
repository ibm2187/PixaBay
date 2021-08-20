package ibm2187.dev.pixabay.model.network.responses


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import ibm2187.dev.pixabay.model.network.dto.Hit

@Keep
data class PixaBayResponse(
    @SerializedName("hits") val hits: List<Hit>,
    @SerializedName("total") val total: Int,
    @SerializedName("totalHits") val totalHits: Int
)