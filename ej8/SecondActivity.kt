package com.example.eje8

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var etDato: EditText
    private lateinit var btnEnviarResultado: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        etDato = findViewById(R.id.etDato)
        btnEnviarResultado = findViewById(R.id.btnEnviarResultado)

        btnEnviarResultado.setOnClickListener {
            val texto = etDato.text.toString()
            if (texto.isBlank()) {
                Toast.makeText(this, "Escribe algo primero", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val resultIntent = Intent().apply {
                putExtra("dato_resultado", texto)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
