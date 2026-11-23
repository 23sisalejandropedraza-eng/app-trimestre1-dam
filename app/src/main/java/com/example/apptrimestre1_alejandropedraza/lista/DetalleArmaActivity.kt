package com.example.apptrimestre1_alejandropedraza.lista

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptrimestre1_alejandropedraza.R

class DetalleArmaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_arma)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val posicion = intent.getIntExtra("POSICION", -1)
        val ivArma: ImageView = findViewById(R.id.ivDetalleArma)
        val tvNombre: TextView = findViewById(R.id.tvDetalleNombre)
        val tvDescripcion: TextView = findViewById(R.id.tvDetalleDescripcion)
        val btnVolver: Button = findViewById(R.id.btnVolverDetalle)

        if (posicion in DatosArmas.listaArmas.indices) {
            val arma = DatosArmas.listaArmas[posicion]
            ivArma.setImageResource(arma.imagen)
            tvNombre.text = arma.nombre
            tvDescripcion.text = arma.descripcion
        }

        btnVolver.setOnClickListener {
            finish()
        }
    }
}