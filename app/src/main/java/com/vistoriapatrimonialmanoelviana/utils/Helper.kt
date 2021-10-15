
package com.vistoriapatrimonialmanoelviana.utils
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.vistoriapatrimonialmanoelviana.ui.LoginActivity
import com.vistoriapatrimonialmanoelviana.ui.activity_main


fun Context.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.login() {
    val intent = Intent(this, activity_main::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent )
}


fun Context.logout() {
    val intent = Intent(this, LoginActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)


}
