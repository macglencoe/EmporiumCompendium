package com.example.emporiumprealpha3.ui.theme

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
                "Macanudo 1968 Robusto",
                13.0, 5.0, "Full",
                Brands[3]
            ),
            Cigar(
                "Arturo Fuente Cuban Corona",
                17.0, 5.0, "Medium",
                Brands[2]
            ),
            Cigar(
                "Romeo y Julieta Reserva Real Twisted Toro",
                8.0, 6.0, "Medium",
                Brands[4]
            ),
            Cigar(
                "ACID Blue Kuba Kuba",
                10.0, 5.0, "Medium",
                Brands[5]
            ),
            Cigar(
                "Baccarat Nicaragua Churchill",
                6.0, 7.0, "Medium",
                Brands[6]
            ),
            Cigar(
                "CAO Brazilia Amazon",
                6.0, 6.0, "Full",
                Brands[7]
            ),
            Cigar(
                "Oliva Serie G Presidente",
                7.0,8.0,"Medium",
                Brands[1]
            ),
            Cigar(
                "Ashton Cabinet No.7",
                13.0,6.0,"Medium",
                Brands[0]
            )
        )
    }
}


