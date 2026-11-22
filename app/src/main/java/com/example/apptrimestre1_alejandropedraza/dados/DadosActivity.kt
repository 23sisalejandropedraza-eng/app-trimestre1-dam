package com.example.apptrimestre1_alejandropedraza.dados

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptrimestre1_alejandropedraza.R
import kotlin.random.Random

class DadosActivity : AppCompatActivity() {

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

        val etCantidadDados: EditText = findViewById(R.id.etCantidadDados)
        val spTipoDado: Spinner = findViewById(R.id.spTipoDado)
        val btnTirar: Button = findViewById(R.id.btnTirar)
        val tvResultados: TextView = findViewById(R.id.tvResultadosIndividuales)
        val tvSuma: TextView = findViewById(R.id.tvSumaDados)

        val tiposDado = listOf("4 caras", "6 caras", "8 caras", "10 caras", "12 caras", "20 caras")
        val carasValores = listOf(4, 6, 8, 10, 12, 20)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposDado)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTipoDado.adapter = adapter
        spTipoDado.setSelection(1)

        btnTirar.setOnClickListener {
            val cantidad = etCantidadDados.text.toString().trim().toIntOrNull()

            if (cantidad == null || cantidad < 1 || cantidad > 20) {
                tvResultados.text = "Introduce entre 1 y 20 dados"
                tvSuma.text = "Suma total: 0"
                return@setOnClickListener
            }

            val caras = carasValores[spTipoDado.selectedItemPosition]
            val listaTiradas = mutableListOf<Int>()
            var suma = 0

            for (i in 1..cantidad) {
                val valor = Random.nextInt(1, caras + 1)
                listaTiradas.add(valor)
                suma += valor
            }

            tvResultados.text = "Resultados: ${listaTiradas.joinToString(", ")}"
            tvSuma.text = "Suma total: $suma"
        }
    }
}