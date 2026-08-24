package com.example.eje4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnEntrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        btnEntrar = findViewById(R.id.btnEntrar)

        btnEntrar.setOnClickListener {
            val user = etUsuario.text.toString()
            val pass = etPassword.text.toString()
            if (user.isBlank() || pass.isBlank()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Bienvenido, $user", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
