package com.sistema.facturacion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sistema.facturacion.R
import com.sistema.facturacion.database.ProductoEntity

class ProductoAdapter(val listaProductos: List<ProductoEntity>) :
    RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombre: TextView = view.findViewById(R.id.txtNombreProducto)
        val txtPrecio: TextView = view.findViewById(R.id.txtPrecioProducto)
        val txtStock: TextView = view.findViewById(R.id.txtStockProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaProductos[position]
        holder.txtNombre.text = producto.nombre
        holder.txtPrecio.text = "Precio: $" + producto.precio
        holder.txtStock.text = "Stock: " + producto.stock
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }
}
