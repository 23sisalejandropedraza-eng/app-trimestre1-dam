package com.example.apptrimestre1_alejandropedraza.lista

import com.example.apptrimestre1_alejandropedraza.R

object DatosArmas {
    val listaArmas = mutableListOf<Arma>()

    fun inicializar() {
        if (listaArmas.isEmpty()) {
            poblar()
        }
    }

    private fun nuevoArma(arma: Arma) {
        listaArmas.add(arma)
    }

    fun poblar() {
        nuevoArma(
            Arma("Gran Espada", "Arma pesada de gran alcance con ataques cargados devastadores.", R.drawable.granespada)
        )
        nuevoArma(
            Arma("Espada Larga", "Katana ágil con gran alcance y medidor de espíritu.", R.drawable.espadalarga)
        )
        nuevoArma(
            Arma("Doble Espada", "Ataques veloces en ráfaga con el modo demonio.", R.drawable.espadasdobles)
        )
        nuevoArma(
            Arma("Hacha Espada", "Arma transformable entre hacha y espada versátil.", R.drawable.hachaespada)
        )
        nuevoArma(
            Arma("Ballesta Pesada", "Especializada en estados alterados y gran daño a distancia.", R.drawable.ballestapesada)
        )
        nuevoArma(
            Arma("Arco", "Alto daño elemental y movilidad constante.", R.drawable.arco)
        )
        nuevoArma(
            Arma("Hacha Cargada", "Revestimiento de impacto devastador con viales.", R.drawable.hachacargada)
        )
        nuevoArma(
            Arma("Glaive Insecto", "Permite saltos aéreos y un Kinsecto de apoyo.", R.drawable.glaiveinsecto)
        )
        nuevoArma(
            Arma("Martillo", "Excelente para destrozar cabezas y aturdir monstruos.", R.drawable.martillo)
        )
        nuevoArma(
            Arma("Cornamusa", "Melodías de recuperación y aumento de ataque.", R.drawable.cornamusa)
        )
    }
}