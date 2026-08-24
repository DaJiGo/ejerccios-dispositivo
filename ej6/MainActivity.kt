package com.example.eje6

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvPantalla: TextView
    private lateinit var gridTeclado: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPantalla = findViewById(R.id.tvPantalla)
        gridTeclado = findViewById(R.id.gridTeclado)

        // Un solo listener recorriendo los hijos del GridLayout
        val listenerComun = View.OnClickListener { view ->
            val tag = view.tag?.toString() ?: return@OnClickListener
            when (tag) {
                "borrar" -> {
                    val textoActual = tvPantalla.text.toString()
                    if (textoActual.isNotEmpty()) {
                        tvPantalla.text = textoActual.dropLast(1)
                    }
                }
                "limpiar" -> {
                    tvPantalla.text = ""
                }
                else -> {
                    tvPantalla.text = tvPantalla.text.toString() + tag
                }
            }
        }

        for (i in 0 until gridTeclado.childCount) {
            val child = gridTeclado.getChildAt(i)
            if (child is Button) {
                child.setOnClickListener(listenerComun)
            }
        }
    }
}
