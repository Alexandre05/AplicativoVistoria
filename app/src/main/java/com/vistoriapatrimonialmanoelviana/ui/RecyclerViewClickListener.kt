package com.vistoriapatrimonialmanoelviana.ui

import android.view.View

import com.vistoriapatrimonialmanoelviana.data.Patrimonio

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClicked(view: View, patrimonio: Patrimonio)


}
