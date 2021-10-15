package com.vistoriapatrimonialmanoelviana.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vistoriapatrimonialmanoelviana.R
import kotlinx.android.synthetic.main.dialog_fragment_add_author.*
import kotlinx.android.synthetic.main.dialog_fragment_add_author.button_add
import kotlinx.android.synthetic.main.dialog_fragment_add_author.input_layout_name
import kotlinx.android.synthetic.main.dialog_fragment_edit_author.*

import com.vistoriapatrimonialmanoelviana.data.Patrimonio
import com.vistoriapatrimonialmanoelviana.ui.AuthorsViewModel

class EditAuthorDialogFragment(
    private val patrimonio: Patrimonio
) : DialogFragment() {

    private lateinit var viewModel: AuthorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(AuthorsViewModel::class.java)
        return inflater.inflate(R.layout.dialog_fragment_edit_author, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        edit_text_Des.setText(patrimonio.descricao)
        edit_localiza.setText(patrimonio.localizacao)
        edit_text_Plac.setText(patrimonio.placa.toString())


        viewModel.result.observe(viewLifecycleOwner, Observer {
            val message = if (it == null) {
                getString(R.string.author_added)
            } else {
                getString(R.string.error, it.message)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            dismiss()
        })
// botão Editar
        button_add.setOnClickListener {
            val descricao = edit_text_Des.text.toString().trim()
            val localizacao= edit_localiza.text.toString().trim()
            val placa=edit_text_Plac.text.toString().trim()
            if (descricao.isEmpty()|| localizacao.isEmpty()) {
                input_layout_name.error = getString(R.string.error_field_required)

                return@setOnClickListener
            }
            patrimonio.descricao = descricao
            patrimonio.localizacao=localizacao
            patrimonio.placa=placa.toInt()
            viewModel.updateAuthor(patrimonio)
        }
    }
}