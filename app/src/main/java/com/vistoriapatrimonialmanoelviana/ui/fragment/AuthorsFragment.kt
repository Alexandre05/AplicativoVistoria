package com.vistoriapatrimonialmanoelviana.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vistoriapatrimonialmanoelviana.R
import kotlinx.android.synthetic.main.fragment_authors.*

import com.vistoriapatrimonialmanoelviana.data.Patrimonio
import com.vistoriapatrimonialmanoelviana.ui.*


class AuthorsFragment : Fragment(), RecyclerViewClickListener {



    private lateinit var viewModel: AuthorsViewModel
    private val adapter = AuthorsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {
        viewModel = ViewModelProviders.of(this).get(AuthorsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_authors, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter.listener = this
        recycler_view_authors.adapter = adapter

        viewModel.fetchFilteredAuthors(1)
        viewModel.fetchFilteredAuthors(2)
        viewModel.fetchFilteredAuthors(3)
        viewModel.fetchFilteredAuthors(4)
        viewModel.fetchFilteredAuthors(5)
        viewModel.fetchFilteredAuthors(6)
        viewModel.fetchAuthors()
       viewModel.getRealtimeUpdates()

        viewModel.authors.observe(viewLifecycleOwner, Observer {
            adapter.setAuthors(it)
        })

        viewModel.author.observe(viewLifecycleOwner, Observer {
            adapter.addAuthor(it)
        })

        button_add.setOnClickListener {
            AddAuthorDialogFragment()
                .show(childFragmentManager, "")
        }
    }

    override fun onRecyclerViewItemClicked(view: View, patrimonio: Patrimonio) {
        when (view.id) {
            R.id.button_edit -> {
                EditAuthorDialogFragment(patrimonio).show(childFragmentManager, "")
            }
            R.id.button_delete -> {
                AlertDialog.Builder(requireContext()).also {
                    it.setTitle(getString(R.string.delete_confirmation))
                    it.setPositiveButton(getString(R.string.yes)) { dialog, which ->
                        viewModel.deleteAuthor(patrimonio)
                    }
                }.create().show()
            }
        }
    }

}
