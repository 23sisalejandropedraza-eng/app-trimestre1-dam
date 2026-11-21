package com.example.apptrimestre1_alejandropedraza.conversor

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
import java.util.Locale

class ConversorActivity : AppCompatActivity() {

    // Valores base en EUR para convertir fácilmente entre monedas
    private val tasasEnEur = mapOf(
        "EUR" to 1.0,
        "USD" to 1.08,
        "GBP" to 0.83
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_conversor)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val usuario = intent.getStringExtra("USUARIO")?.trim().orEmpty()
        val tvSaludo: TextView = findViewById(R.id.tvSaludoConversor)
        tvSaludo.text = if (usuario.isNotEmpty()) {
            "Conversor - $usuario"
        } else {
            "Conversor de moneda"
        }

        val etCantidad: EditText = findViewById(R.id.etCantidad)
        val spOrigen: Spinner = findViewById(R.id.spOrigen)
        val spDestino: Spinner = findViewById(R.id.spDestino)
        val btnConvertir: Button = findViewById(R.id.btnConvertir)
        val tvResultado: TextView = findViewById(R.id.tvResultadoConversion)

        val monedas = listOf("EUR", "USD", "GBP")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, monedas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spOrigen.adapter = adapter
        spDestino.adapter = adapter
        spDestino.setSelection(1) // Por defecto de EUR a USD

        btnConvertir.setOnClickListener {
            val textoCantidad = etCantidad.text.toString().trim().replace(',', '.')
            val cantidad = textoCantidad.toDoubleOrNull()

            if (cantidad == null || cantidad < 0.0) {
                tvResultado.text = "Introduce una cantidad válida"
                return@setOnClickListener
            }

            val monedaOrigen = spOrigen.selectedItem.toString()
            val monedaDestino = spDestino.selectedItem.toString()

            val tasaOrigen = tasasEnEur[monedaOrigen] ?: 1.0
            val tasaDestino = tasasEnEur[monedaDestino] ?: 1.0

            // Pasamos primero a EUR y luego a la moneda destino
            val cantidadEnEur = cantidad / tasaOrigen
            val convertido = cantidadEnEur * tasaDestino

            val formateado = String.format(Locale.US, "%.2f", convertido).replace('.', ',')
            tvResultado.text = "$formateado $monedaDestino"
        }
    }
}