package com.vistoriapatrimonialmanoelviana.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vistoriapatrimonialmanoelviana.R
import kotlinx.android.synthetic.main.recycler_view_author.view.*

import com.vistoriapatrimonialmanoelviana.data.Patrimonio

class AuthorsAdapter : RecyclerView.Adapter<AuthorsAdapter.AuthorViewModel>() {

    private var patrimonios = mutableListOf<Patrimonio>()
    var listener: RecyclerViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AuthorViewModel(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_author, parent, false)


    )

    override fun getItemCount() = patrimonios.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AuthorViewModel, position: Int) {
        holder.view.text_view_name.text = patrimonios[position].descricao
        holder.view.text_view_city_votes.text =" Local Item : ${patrimonios[position].localizacao}"
        holder.view.text_view_Placa.text="Nº Patrimonio:${patrimonios[position].placa}"
        holder.view.button_edit.setOnClickListener {
            listener?.onRecyclerViewItemClicked(it, patrimonios[position])
        }
        holder.view.button_delete.setOnClickListener {
            listener?.onRecyclerViewItemClicked(it, patrimonios[position])
        }
    }

    fun setAuthors(patrimonios: List<Patrimonio>) {
       this.patrimonios = patrimonios as MutableList<Patrimonio>
        notifyDataSetChanged()
    }

    fun addAuthor(patrimonio: Patrimonio) {
        if (!patrimonios.contains(patrimonio)) {
            patrimonios.add(patrimonio)
        } else {
            val index = patrimonios.indexOf(patrimonio)
            if (patrimonio.isDeleted) {
                patrimonios.removeAt(index)
            } else {
                patrimonios[index] = patrimonio
            }
        }
        notifyDataSetChanged()
    }

    class AuthorViewModel(val view: View) : RecyclerView.ViewHolder(view)
}