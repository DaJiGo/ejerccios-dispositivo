package com.example.eje1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private lateinit var switchRecordar: Switch
    private lateinit var btnLogin: Button
    private lateinit var tvEstado: TextView

    private val PREFS_NAME = "login_prefs"
    private val KEY_USER = "usuario"
    private val KEY_PASS = "password"
    private val KEY_RECORDAR = "recordar"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        switchRecordar = findViewById(R.id.switchRecordar)
        btnLogin = findViewById(R.id.btnLogin)
        tvEstado = findViewById(R.id.tvEstado)

        cargarDatosGuardados()

        switchRecordar.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            if (isChecked) {
                prefs.edit()
                    .putString(KEY_USER, etUsuario.text.toString())
                    .putString(KEY_PASS, etPassword.text.toString())
                    .putBoolean(KEY_RECORDAR, true)
                    .apply()
                tvEstado.text = "Contraseña guardada"
            } else {
                prefs.edit()
                    .remove(KEY_USER)
                    .remove(KEY_PASS)
                    .putBoolean(KEY_RECORDAR, false)
                    .apply()
                tvEstado.text = "Contraseña eliminada"
            }
        }

        btnLogin.setOnClickListener {
            val user = etUsuario.text.toString()
            val pass = etPassword.text.toString()
            if (user.isBlank() || pass.isBlank()) {
                tvEstado.text = "Completa usuario y contraseña"
            } else {
                tvEstado.text = "Sesión iniciada como $user"
            }
        }
    }

    private fun cargarDatosGuardados() {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val recordar = prefs.getBoolean(KEY_RECORDAR, false)
        switchRecordar.isChecked = recordar
        if (recordar) {
            etUsuario.setText(prefs.getString(KEY_USER, ""))
            etPassword.setText(prefs.getString(KEY_PASS, ""))
        }
    }
}
