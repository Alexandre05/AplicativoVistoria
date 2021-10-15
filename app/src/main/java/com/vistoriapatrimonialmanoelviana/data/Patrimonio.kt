package com.vistoriapatrimonialmanoelviana.data

import com.google.firebase.database.Exclude

//private val cities = listOf("Bangalore", "Mumbai", "Ranchi", "Kolkata", "Hyderabad", "Pune")

data class Patrimonio(
    @get:Exclude
    var id: String? = null,
    var descricao: String? = null,
    var localizacao: String? = null,
    var placa: Int? = null,
    @get:Exclude
    var isDeleted: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return if (other is Patrimonio) {
            other.id == id
        } else false
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (descricao?.hashCode() ?: 0)
        return result
    }

}