package com.sistema.facturacion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sistema.facturacion.R
import com.sistema.facturacion.database.FacturaEntity

class FacturaAdapter(val listaFacturas: List<FacturaEntity>) :
    RecyclerView.Adapter<FacturaAdapter.FacturaViewHolder>() {

    class FacturaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtId: TextView = view.findViewById(R.id.txtFacturaId)
        val txtCliente: TextView = view.findViewById(R.id.txtFacturaCliente)
        val txtProducto: TextView = view.findViewById(R.id.txtFacturaProducto)
        val txtTotal: TextView = view.findViewById(R.id.txtFacturaTotal)
        val txtFecha: TextView = view.findViewById(R.id.txtFacturaFecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_factura, parent, false)
        return FacturaViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacturaViewHolder, position: Int) {
        val factura = listaFacturas[position]
        holder.txtId.text = "Factura #" + factura.idFactura
        holder.txtCliente.text = "Cliente: " + factura.clienteRnc
        holder.txtProducto.text = "Producto ID: " + factura.productoId + "  Cantidad: " + factura.cantidad
        holder.txtTotal.text = "Total: $" + factura.total
        holder.txtFecha.text = factura.fecha
    }

    override fun getItemCount(): Int {
        return listaFacturas.size
    }
}
