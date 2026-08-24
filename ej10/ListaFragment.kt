package com.example.eje10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaFragment : Fragment() {

    companion object {
        private const val ARG_CATEGORIA = "categoria"

        fun newInstance(categoria: String): ListaFragment {
            val fragment = ListaFragment()
            val args = Bundle()
            args.putString(ARG_CATEGORIA, categoria)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_lista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoria = arguments?.getString(ARG_CATEGORIA) ?: "General"

        val listaAnimales = when (categoria) {
            "Domésticos" -> listOf(
                Animal("Perro", "Fiel compañero del hogar", android.R.drawable.ic_menu_gallery),
                Animal("Gato", "Independiente y cariñoso", android.R.drawable.ic_menu_gallery),
                Animal("Conejo", "Pequeño y silencioso", android.R.drawable.ic_menu_gallery)
            )
            "Salvajes" -> listOf(
                Animal("León", "Rey de la selva", android.R.drawable.ic_menu_gallery),
                Animal("Elefante", "El más grande de tierra", android.R.drawable.ic_menu_gallery),
                Animal("Tigre", "Cazador solitario", android.R.drawable.ic_menu_gallery)
            )
            else -> listOf(
                Animal("Delfín", "Inteligente nadador", android.R.drawable.ic_menu_gallery),
                Animal("Águila", "Ave de rapiña", android.R.drawable.ic_menu_gallery)
            )
        }

        val rv = view.findViewById<RecyclerView>(R.id.rvAnimales)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = AnimalAdapter(listaAnimales)
    }
}
