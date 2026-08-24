package com.sistema.facturacion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sistema.facturacion.adapters.ProductoAdapter

class ProductosActivity : AppCompatActivity() {

    lateinit var recyclerProductos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        recyclerProductos = findViewById(R.id.recyclerProductos)
        recyclerProductos.layoutManager = LinearLayoutManager(this)

        Thread {
            val dao = FacturacionApp.database.facturacionDao()
            val productos = dao.getAllProductos()

            runOnUiThread {
                recyclerProductos.adapter = ProductoAdapter(productos)
            }
        }.start()
    }
}
