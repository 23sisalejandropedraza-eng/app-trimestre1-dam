package com.example.apptrimestre1_alejandropedraza.lista

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptrimestre1_alejandropedraza.R

class ListaActivity : AppCompatActivity() {

    private lateinit var lvArmas: ListView
    private lateinit var tvSeleccionActual: TextView
    private lateinit var adapter: ArrayAdapter<Arma>
    private var posicionSeleccionada = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: Toolbar = findViewById(R.id.toolbarLista)
        setSupportActionBar(toolbar)

        DatosArmas.inicializar()

        val usuario = intent.getStringExtra("USUARIO")?.trim().orEmpty()
        supportActionBar?.title = if (usuario.isNotEmpty()) {
            "Armas - $usuario"
        } else {
            "Armas Monster Hunter"
        }

        tvSeleccionActual = findViewById(R.id.tvSeleccionActual)
        lvArmas = findViewById(R.id.lvArmas)

        adapter = object : ArrayAdapter<Arma>(this, R.layout.item_arma, DatosArmas.listaArmas) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val vista = convertView ?: layoutInflater.inflate(R.layout.item_arma, parent, false)
                val arma = DatosArmas.listaArmas[position]

                val ivArma: ImageView = vista.findViewById(R.id.ivItemArma)
                val tvNombre: TextView = vista.findViewById(R.id.tvItemNombreArma)

                ivArma.setImageResource(arma.imagen)
                tvNombre.text = arma.nombre

                if (position == posicionSeleccionada) {
                    vista.setBackgroundColor(0xFFDDEEFF.toInt())
                } else {
                    vista.setBackgroundColor(0x00000000)
                }

                return vista
            }
        }

        lvArmas.adapter = adapter

        lvArmas.setOnItemClickListener { _, _, position, _ ->
            posicionSeleccionada = position
            tvSeleccionActual.text = "Seleccionada: ${DatosArmas.listaArmas[position].nombre}"
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
        if (posicionSeleccionada in DatosArmas.listaArmas.indices) {
            tvSeleccionActual.text = "Seleccionada: ${DatosArmas.listaArmas[posicionSeleccionada].nombre}"
        } else {
            posicionSeleccionada = -1
            tvSeleccionActual.text = "Ninguna arma seleccionada"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.accion_anadir -> {
                val intent = Intent(this, FormularioArmaActivity::class.java)
                intent.putExtra("POSICION", -1)
                startActivity(intent)
                true
            }

            R.id.accion_editar -> {
                if (posicionSeleccionada == -1) {
                    Toast.makeText(this, "Selecciona un arma para editar", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this, FormularioArmaActivity::class.java)
                    intent.putExtra("POSICION", posicionSeleccionada)
                    startActivity(intent)
                }
                true
            }

            R.id.accion_eliminar -> {
                if (posicionSeleccionada == -1) {
                    Toast.makeText(this, "Selecciona un arma para eliminar", Toast.LENGTH_SHORT).show()
                } else {
                    val eliminada = DatosArmas.listaArmas[posicionSeleccionada].nombre
                    DatosArmas.listaArmas.removeAt(posicionSeleccionada)
                    posicionSeleccionada = -1
                    tvSeleccionActual.text = "Ninguna arma seleccionada"
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Eliminada: $eliminada", Toast.LENGTH_SHORT).show()
                }
                true
            }

            R.id.accion_detalle -> {
                if (posicionSeleccionada == -1) {
                    Toast.makeText(this, "Selecciona un arma para ver detalles", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this, DetalleArmaActivity::class.java)
                    intent.putExtra("POSICION", posicionSeleccionada)
                    startActivity(intent)
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}