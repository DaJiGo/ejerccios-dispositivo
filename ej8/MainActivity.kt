package com.example.eje8

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResultado: TextView
    private lateinit var btnAbrirSecond: Button

    // Reemplazo moderno de startActivityForResult (deprecado)
    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val dato = result.data?.getStringExtra("dato_resultado") ?: "sin dato"
            tvResultado.text = "Resultado recibido: $dato"
        } else {
            tvResultado.text = "Cancelado por el usuario"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResultado = findViewById(R.id.tvResultado)
        btnAbrirSecond = findViewById(R.id.btnAbrirSecond)

        btnAbrirSecond.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            launcher.launch(intent)
        }
    }
}
