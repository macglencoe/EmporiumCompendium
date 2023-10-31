package com.example.emporiumprealpha3.model

import androidx.compose.ui.graphics.painter.Painter
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.emporiumprealpha3.data.DemoData

data class Cigar(
    val id:String,
    val title:String,
    val brandId: String,
    val price:Double? = null,
    val length:Double? = null,
    val ringGauge:Double? = null,
    val strength:String? = null,
    val wrapper: String? = null,
    val binder: String? = null,
    val filler: List<String>? = null,
    val tastingNotes: List<String>? = null,
    val description: String? = null,
    val img_src: String? = null
) {

    fun getBrand() : Brand {
        return DemoData.Brands.first { it.id == brandId}
    }
    fun generateSizeInfoMap() : Map<String, String> {
        return mapOf<String, String?>(
            "Length" to this.length?.toString(),
            "Ring Gauge" to this.ringGauge?.toString()
        ).filterValues { it != null }.mapValues { it -> it.value as String }
    }

    fun generateBlendInfoMap() : Map<String, String> {
        return mapOf<String, String?>(
            "Body" to this.strength,
            "Wrapper" to this.wrapper,
            "Binder" to this.binder,
            "Filler" to this.filler?.joinToString(", "),
            "Tasting Notes" to this.tastingNotes?.joinToString(", ")
        ).filterValues { it != null }.mapValues { it -> it.value as String}
    }
}