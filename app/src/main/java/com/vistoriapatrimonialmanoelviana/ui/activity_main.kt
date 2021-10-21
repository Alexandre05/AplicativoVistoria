package com.vistoriapatrimonialmanoelviana.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.navigation.Navigation
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog

import androidx.navigation.ui.NavigationUI
import com.google.firebase.auth.FirebaseAuth
import com.vistoriapatrimonialmanoelviana.R
import com.vistoriapatrimonialmanoelviana.utils.logout
//import kotlinx.android.synthetic.main.activity_home.*
//import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class activity_main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)


//val busca=findViewById<SearchView>(R.id.busca)

        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(nav_view, navController)
        NavigationUI.setupActionBarWithNavController(
            this,
            navController, drawer_layout
        )
    }





    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.fragment),
            drawer_layout

        )

    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.opcoes, menu)
        return true

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.action_logout) {

            AlertDialog.Builder(this).apply {
                setTitle("Deseja Mesmo Sair?")
                setPositiveButton("Sim") { _, _ ->

                    FirebaseAuth.getInstance().signOut()
                    logout()

                }
                setNegativeButton("Cancelar") { _, _ ->
                }
            }.create().show()

        }
        return super.onOptionsItemSelected(item)


    }





}

