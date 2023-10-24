package com.example.emporiumprealpha3.ui.theme

data class DemoData(val test: Int) {
    companion object {
        val Brands: List<String> = listOf(
            "Ashton", "Oliva", "Arturo Fuente", "Macanudo"
        )
        val Cigars: List<Cigar> = listOf(
            Cigar(
                "Macanudo 1968 Robusto",
                13.0, 5.0, "Full"
            ),
            Cigar(
                "Arturo Fuente Cuban Corona",
                17.0, 5.0, "Medium"
            ),
            Cigar(
                "Romeo y Julieta Reserva Real Twisted Toro",
                8.0, 6.0, "Medium"
            ),
            Cigar(
                "ACID Blue Kuba Kuba",
                10.0, 5.0, "Medium"
            ),
            Cigar(
                "Baccarat Nicaragua Churchill",
                6.0, 7.0, "Medium"
            ),
            Cigar(
                "CAO Brazilia Amazon",
                6.0, 6.0, "Full"
            ),
            Cigar(
                "Oliva Serie G Presidente",
                7.0,8.0,"Medium"
            ),
            Cigar(
                "Ashton Cabinet No.7",
                13.0,6.0,"Medium"
            )
        )
    }
}
class Cigar(title:String, price:Double, length:Double, strength:String) {
    val Title: String = title
    val Price: Double = price
    val Length: Double = length
    val Strength: String = strength
}

