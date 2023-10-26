package com.example.emporiumprealpha3.data

import com.example.emporiumprealpha3.model.Brand
import com.example.emporiumprealpha3.model.Cigar

data class DemoData(val test: Int) {
    companion object {
        val Brands: List<Brand> = listOf(
            Brand("Ashton"),
            Brand("Oliva"),
            Brand("Arturo Fuente"),
            Brand("Macanudo"),
            Brand("Romeo y Julieta"),
            Brand("ACID"),
            Brand("Baccarat"),
            Brand("CAO")
        )
        val Cigars: List<Cigar> = listOf(
            Cigar(
                id = "1",
                title = "Macanudo 1968 Robusto",
                price = 13.0, length = 5.0, ringGauge = 50.0,
                strength = "Full",
                brand = Brands[3], wrapper = "Honduran Olancho San Agustin",
                binder = "Connecticut Habano",
                filler = listOf("Dominican", "Nicaraguan Estelli", "Nicaraguan Ometepe"),
                tastingNotes = listOf("Pepper", "Cocoa", "Cedar", "Coffee"),
                description = """
                    Crafted in celebration of Macanudo’s iconic legacy, Macanudo 1968 leaves expectations behind by providing a full, flavorful and intricate smoke for fans of complex cigars. But don’t let the new approach fool you, 1968 still delivers the intricately balanced flavor and inherent sweetness present in all of our cigars from the rich soils in which they are grown. The result is a cigar that has received unanimous critical acclaim with 90+ ratings from Cigar Aficionado and Cigar Insider.
                """.trimIndent()
            ),
            Cigar(
                id = "2", title = "Arturo Fuente Cuban Corona",
                price = 17.0, length = 5.25, ringGauge = 45.0, strength = "Medium",
                brand = Brands[2]
            ),
            Cigar(
                id = "3", title = "Romeo y Julieta Reserva Real Twisted Toro",
                price = 8.0, length = 6.0, ringGauge = 54.0, strength = "Medium",
                brand = Brands[4]
            ),
            Cigar(
                id = "4", title = "ACID Blue Kuba Kuba",
                price = 10.0, length = 5.0, ringGauge = 54.0, strength = "Medium",
                brand = Brands[5]
            ),
            Cigar(
                id = "5", title = "Baccarat Nicaragua Churchill",
                price = 6.0, length = 7.0, ringGauge = 50.0, strength = "Medium",
                brand = Brands[6]
            ),
            Cigar(
                id = "6", title = "CAO Brazilia Amazon",
                price = 6.0, length = 6.0, ringGauge = 60.0, strength = "Full",
                brand = Brands[7]
            ),
            Cigar(
                id = "7", title = "Oliva Serie G Presidente",
                price = 7.0, length = 8.0, ringGauge = 52.0, strength = "Medium",
                brand = Brands[1]
            ),
            Cigar(
                id = "8", title = "Ashton Cabinet No.7",
                price = 13.0, length = 6.0, ringGauge = 52.0, strength = "Medium",
                brand = Brands[0]
            )
        )
    }
}


