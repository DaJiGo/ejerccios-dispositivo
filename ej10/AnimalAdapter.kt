package com.example.eje10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter(private val listaAnimales: List<Animal>) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAnimal: ImageView = itemView.findViewById(R.id.ivAnimal)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombreAnimal)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcionAnimal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(vista)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = listaAnimales[position]
        holder.tvNombre.text = animal.nombre
        holder.tvDescripcion.text = animal.descripcion
        holder.ivAnimal.setImageResource(animal.imagenResId)
    }

    override fun getItemCount(): Int = listaAnimales.size
}
