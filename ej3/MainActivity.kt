package com.example.eje3

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var tvPorcentaje: TextView
    private lateinit var btnIniciar: Button
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        tvPorcentaje = findViewById(R.id.tvPorcentaje)
        btnIniciar = findViewById(R.id.btnIniciar)

        btnIniciar.setOnClickListener {
            iniciarCarga()
        }
    }

    private fun iniciarCarga() {
        btnIniciar.isEnabled = false
        progressBar.visibility = View.VISIBLE
        actualizarProgreso(0)

        handler.postDelayed({
            actualizarProgreso(30)

            handler.postDelayed({
                actualizarProgreso(60)

                handler.postDelayed({
                    actualizarProgreso(100)
                    progressBar.visibility = View.GONE
                    btnIniciar.isEnabled = true
                }, 1000)

            }, 1000)

        }, 1000)
    }

    private fun actualizarProgreso(valor: Int) {
        progressBar.progress = valor
        tvPorcentaje.text = "$valor%"
    }
}
