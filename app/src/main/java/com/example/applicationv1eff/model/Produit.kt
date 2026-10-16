package com.example.applicationv1eff.model

data class Produit(
    val id: Int,
    val name: String,
    val quantity: Int
) {
    companion object {
        var lstProducts = mutableListOf<Produit>()
    }
}
