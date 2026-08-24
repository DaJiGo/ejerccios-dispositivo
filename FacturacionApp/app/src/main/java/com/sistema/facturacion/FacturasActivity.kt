package com.sistema.facturacion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sistema.facturacion.adapters.FacturaAdapter

class FacturasActivity : AppCompatActivity() {

    lateinit var recyclerFacturas: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facturas)

        recyclerFacturas = findViewById(R.id.recyclerFacturas)
        recyclerFacturas.layoutManager = LinearLayoutManager(this)

        Thread {
            val dao = FacturacionApp.database.facturacionDao()
            val facturas = dao.getAllFacturas()

            runOnUiThread {
                recyclerFacturas.adapter = FacturaAdapter(facturas)
            }
        }.start()
    }
}
