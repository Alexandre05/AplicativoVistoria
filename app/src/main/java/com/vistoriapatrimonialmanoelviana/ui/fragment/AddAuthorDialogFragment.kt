package com.vistoriapatrimonialmanoelviana.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders.of
import com.vistoriapatrimonialmanoelviana.R
import kotlinx.android.synthetic.main.dialog_fragment_add_author.*
import kotlinx.android.synthetic.main.dialog_fragment_add_author.button_add
import kotlinx.android.synthetic.main.dialog_fragment_add_author.edit_text_name
import kotlinx.android.synthetic.main.dialog_fragment_add_author.input_layout_name
import kotlinx.android.synthetic.main.dialog_fragment_edit_author.*

import com.vistoriapatrimonialmanoelviana.data.Patrimonio
import com.vistoriapatrimonialmanoelviana.ui.AuthorsViewModel

class AddAuthorDialogFragment : DialogFragment() {

    private lateinit var viewModel: AuthorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = of(this).get(AuthorsViewModel::class.java)
        return inflater.inflate(R.layout.dialog_fragment_add_author, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.result.observe(viewLifecycleOwner, Observer {
            val message = if (it == null) {
                getString(R.string.author_added)
            } else {
                getString(R.string.error, it.message)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            dismiss()
        })
// aqui faz o cadastro
        button_add.setOnClickListener {
            val descricao = edit_text_name.text.toString().trim()
            val localizacao= edit_text_localizacao.text.toString().trim()
            val placa=edit_text_Placa.text.toString().trim()
            if (descricao.isEmpty() || localizacao.isEmpty()) {
                input_layout_name.error = getString(R.string.error_field_required)

                return@setOnClickListener
            }
            val patrimonio = Patrimonio()
            patrimonio.descricao = descricao
            patrimonio.localizacao=localizacao
            patrimonio.placa=placa.toInt()
            viewModel.addAuthor(patrimonio)
        }
    }
}