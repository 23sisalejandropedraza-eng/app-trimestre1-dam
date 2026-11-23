package com.example.apptrimestre1_alejandropedraza.lista

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptrimestre1_alejandropedraza.R

class FormularioArmaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario_arma)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvTitulo: TextView = findViewById(R.id.tvTituloFormulario)
        val etNombre: EditText = findViewById(R.id.etNombreArma)
        val etDescripcion: EditText = findViewById(R.id.etDescripcionArma)
        val btnGuardar: Button = findViewById(R.id.btnGuardarArma)

        val posicion = intent.getIntExtra("POSICION", -1)
        val esEdicion = posicion in DatosArmas.listaArmas.indices

        if (esEdicion) {
            val armaActual = DatosArmas.listaArmas[posicion]
            tvTitulo.text = "Editar arma"
            etNombre.setText(armaActual.nombre)
            etDescripcion.setText(armaActual.descripcion)
        } else {
            tvTitulo.text = "Añadir arma"
        }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val descripcion = etDescripcion.text.toString().trim()

            if (nombre.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Rellena nombre y descripción", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (esEdicion) {
                DatosArmas.listaArmas[posicion].nombre = nombre
                DatosArmas.listaArmas[posicion].descripcion = descripcion
                Toast.makeText(this, "Arma actualizada", Toast.LENGTH_SHORT).show()
            } else {
                val nuevaArma = Arma(
                    nombre = nombre,
                    descripcion = descripcion,
                    imagen = R.drawable.granespada
                )
                DatosArmas.listaArmas.add(nuevaArma)
                Toast.makeText(this, "Arma añadida", Toast.LENGTH_SHORT).show()
            }

            finish()
        }
    }
}