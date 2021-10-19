package com.vistoriapatrimonialmanoelviana.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.vistoriapatrimonialmanoelviana.R
import kotlinx.android.synthetic.main.activity_registrar.*

import com.vistoriapatrimonialmanoelviana.utils.login
import com.vistoriapatrimonialmanoelviana.utils.toast
import kotlinx.android.synthetic.main.activity_registrar.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        mAuth = FirebaseAuth.getInstance()


        button_register.setOnClickListener {
            val email = text_email.text.toString().trim()
            val nome_completo=text_nome.text.toString().trim()
            val password = edit_text_password.text.toString().trim()

            if (email.isEmpty()) {
                text_email.error = "Email obrigatório"
                text_email.requestFocus()
                return@setOnClickListener
            }

            if(nome_completo.isEmpty()){
                text_nome.error="Nome Obrigatório"
                text_nome.requestFocus()
                return@setOnClickListener


            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                text_email.error = "É necessário um email válido"
                text_email.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                edit_text_password.error = "Senha de 6 caracteres necessária"
                edit_text_password.requestFocus()
                return@setOnClickListener
            }

            registerUser(email, password)

        }

        text_view_login.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

    private fun registerUser(email: String, password: String) {
        progressbar.visibility = View.VISIBLE
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                progressbar.visibility = View.GONE
                if (task.isSuccessful) {
                    login()
                } else {
                    task.exception?.message?.let {
                        toast(it)
                    }
                }
            }

    }

    override fun onStart() {
        super.onStart()
        mAuth.currentUser?.let {
            login()
        }
    }
}
