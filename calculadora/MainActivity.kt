package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvOperacion: TextView
    private lateinit var tvResultado: TextView

    // Estado de la calculadora
    private var numeroActual: String = "0"
    private var numeroAnterior: String = ""
    private var operadorSeleccionado: String? = null
    private var resultadoMostrado: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOperacion = findViewById(R.id.tvOperacion)
        tvResultado = findViewById(R.id.tvResultado)

        // Buscamos todos los botones directamente por id y les asignamos
        // el mismo listener (más simple y explícito que recorrer el GridLayout).
        val idsBotones = listOf(
            R.id.btnLimpiar, R.id.btnBorrar, R.id.btnPorcentaje, R.id.btnDividir,
            R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnMultiplicar,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btnRestar,
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btnSumar,
            R.id.btnPunto, R.id.btn0, R.id.btnSigno, R.id.btnIgual
        )

        val listenerComun = View.OnClickListener { view ->
            val tag = (view as Button).tag?.toString() ?: return@OnClickListener
            manejarBoton(tag)
        }

        for (id in idsBotones) {
            findViewById<Button>(id).setOnClickListener(listenerComun)
        }

        actualizarPantalla()
    }

    private fun manejarBoton(tag: String) {
        when (tag) {
            "C" -> limpiarTodo()
            "DEL" -> borrarUltimoDigito()
            "%" -> aplicarPorcentaje()
            "SIGNO" -> cambiarSigno()
            "+", "-", "*", "/" -> seleccionarOperador(tag)
            "=" -> calcularResultado()
            "." -> agregarPunto()
            else -> agregarDigito(tag)
        }
        actualizarPantalla()
    }

    private fun limpiarTodo() {
        numeroActual = "0"
        numeroAnterior = ""
        operadorSeleccionado = null
        resultadoMostrado = false
    }

    private fun borrarUltimoDigito() {
        numeroActual = if (numeroActual.length > 1) {
            numeroActual.dropLast(1)
        } else {
            "0"
        }
    }

    private fun agregarDigito(digito: String) {
        if (resultadoMostrado) {
            numeroActual = digito
            resultadoMostrado = false
        } else {
            numeroActual = if (numeroActual == "0") digito else numeroActual + digito
        }
    }

    private fun agregarPunto() {
        if (resultadoMostrado) {
            numeroActual = "0."
            resultadoMostrado = false
            return
        }
        if (!numeroActual.contains(".")) {
            numeroActual += "."
        }
    }

    private fun cambiarSigno() {
        numeroActual = if (numeroActual.startsWith("-")) {
            numeroActual.substring(1)
        } else {
            if (numeroActual == "0") "0" else "-$numeroActual"
        }
    }

    private fun aplicarPorcentaje() {
        val valor = numeroActual.toDoubleOrNull() ?: return
        numeroActual = formatearNumero(valor / 100.0)
    }

    private fun seleccionarOperador(operador: String) {
        if (operadorSeleccionado != null && numeroAnterior.isNotEmpty() && !resultadoMostrado) {
            // Si ya había una operación pendiente, la resolvemos primero (cálculo en cadena)
            calcularResultado()
        }
        numeroAnterior = numeroActual
        operadorSeleccionado = operador
        resultadoMostrado = true // el próximo dígito empieza un número nuevo
    }

    private fun calcularResultado() {
        val operador = operadorSeleccionado ?: return
        if (numeroAnterior.isEmpty()) return

        val n1 = numeroAnterior.toDoubleOrNull() ?: return
        val n2 = numeroActual.toDoubleOrNull() ?: return

        val resultado = when (operador) {
            "+" -> n1 + n2
            "-" -> n1 - n2
            "*" -> n1 * n2
            "/" -> {
                if (n2 == 0.0) {
                    tvResultado.text = "Error"
                    numeroActual = "0"
                    numeroAnterior = ""
                    operadorSeleccionado = null
                    resultadoMostrado = true
                    return
                }
                n1 / n2
            }
            else -> n2
        }

        numeroActual = formatearNumero(resultado)
        numeroAnterior = ""
        operadorSeleccionado = null
        resultadoMostrado = true
    }

    private fun formatearNumero(valor: Double): String {
        return if (valor == valor.toLong().toDouble()) {
            valor.toLong().toString()
        } else {
            valor.toString()
        }
    }

    private fun simboloOperador(op: String): String = when (op) {
        "/" -> "÷"
        "*" -> "×"
        "-" -> "−"
        else -> op
    }

    private fun actualizarPantalla() {
        tvResultado.text = numeroActual
        tvOperacion.text = if (operadorSeleccionado != null && numeroAnterior.isNotEmpty()) {
            "$numeroAnterior ${simboloOperador(operadorSeleccionado!!)}"
        } else {
            ""
        }
    }
}
