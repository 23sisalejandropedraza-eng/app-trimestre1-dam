package com.example.apptrimestre1_alejandropedraza

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptrimestre1_alejandropedraza.calculadora.CalculadoraActivity
import com.example.apptrimestre1_alejandropedraza.conversor.ConversorActivity
import com.example.apptrimestre1_alejandropedraza.dados.DadosActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNombre: EditText = findViewById(R.id.etNombre)
        val btnCalculadora: Button = findViewById(R.id.btnCalculadora)
        val btnConversor: Button = findViewById(R.id.btnConversor)
        val btnJuego: Button = findViewById(R.id.btnJuego)
        val btnLista: Button = findViewById(R.id.btnLista)

        btnCalculadora.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            intent.putExtra("USUARIO", etNombre.text.toString())
            startActivity(intent)
        }

        btnConversor.setOnClickListener {
            val intent = Intent(this, ConversorActivity::class.java)
            intent.putExtra("USUARIO", etNombre.text.toString())
            startActivity(intent)
        }

        btnJuego.setOnClickListener {
            val intent = Intent(this, DadosActivity::class.java)
            intent.putExtra("USUARIO", etNombre.text.toString())
            startActivity(intent)
        }

    }
}