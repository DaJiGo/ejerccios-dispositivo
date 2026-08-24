package com.example.eje9

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tvSeleccion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        tvSeleccion = findViewById(R.id.tvSeleccion)

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_perfil -> {
                tvSeleccion.text = "Seleccionaste: Perfil"
                return true
            }
            R.id.action_configuracion -> {
                tvSeleccion.text = "Seleccionaste: Configuración"
                return true
            }
            R.id.action_salir -> {
                tvSeleccion.text = "Seleccionaste: Salir"
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
