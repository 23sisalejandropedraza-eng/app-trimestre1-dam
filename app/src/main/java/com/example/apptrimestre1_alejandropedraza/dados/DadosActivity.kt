package com.example.apptrimestre1_alejandropedraza.dados

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptrimestre1_alejandropedraza.R
import kotlin.random.Random

class DadosActivity : AppCompatActivity() {

    private var puntosTotales = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dados)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val usuario = intent.getStringExtra("USUARIO")?.trim().orEmpty()
        val tvSaludo: TextView = findViewById(R.id.tvSaludoDados)
        tvSaludo.text = if (usuario.isNotEmpty()) {
            "Juego de dados - $usuario"
        } else {
            "Juego de dados"
        }

        val tvDado1: TextView = findViewById(R.id.tvDado1)
        val tvDado2: TextView = findViewById(R.id.tvDado2)
        val tvResultadoTirada: TextView = findViewById(R.id.tvResultadoTirada)
        val tvPuntosTotales: TextView = findViewById(R.id.tvPuntosTotales)
        val btnTirar: Button = findViewById(R.id.btnTirar)
        val btnReiniciar: Button = findViewById(R.id.btnReiniciarDados)

        btnTirar.setOnClickListener {
            val d1 = Random.nextInt(1, 7)
            val d2 = Random.nextInt(1, 7)
            val suma = d1 + d2

            tvDado1.text = d1.toString()
            tvDado2.text = d2.toString()

            if (d1 == d2) {
                val bonus = suma * 2
                puntosTotales += bonus
                tvResultadoTirada.text = "¡Dobles ($d1 y $d2)! Sumas $bonus puntos"
            } else {
                puntosTotales += suma
                tvResultadoTirada.text = "Has sacado $d1 y $d2 (Total: $suma)"
            }

            tvPuntosTotales.text = "Puntos acumulados: $puntosTotales"
        }

        btnReiniciar.setOnClickListener {
            puntosTotales = 0
            tvDado1.text = "1"
            tvDado2.text = "1"
            tvResultadoTirada.text = "Pulsa tirar para empezar"
            tvPuntosTotales.text = "Puntos acumulados: 0"
        }
    }
}