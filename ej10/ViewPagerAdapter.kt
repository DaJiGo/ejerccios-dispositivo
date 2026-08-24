package com.example.eje10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val titulos = listOf("Domésticos", "Salvajes", "Marinos")

    override fun getCount(): Int = titulos.size

    override fun getItem(position: Int): Fragment {
        return ListaFragment.newInstance(titulos[position])
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titulos[position]
    }
}
