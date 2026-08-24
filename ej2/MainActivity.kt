package com.example.eje2

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var rgGenero: RadioGroup
    private lateinit var btnConfirmar: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rgGenero = findViewById(R.id.rgGenero)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        tvResultado = findViewById(R.id.tvResultado)

        btnConfirmar.setOnClickListener {
            val idSeleccionado = rgGenero.checkedRadioButtonId
            if (idSeleccionado == -1) {
                tvResultado.text = "Debes seleccionar una opción"
            } else {
                val radioSeleccionado = findViewById<android.widget.RadioButton>(idSeleccionado)
                tvResultado.text = "Seleccionaste: ${radioSeleccionado.text}"
            }
        }
    }
}
