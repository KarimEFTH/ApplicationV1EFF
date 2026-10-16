package com.example.applicationv1eff.db


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produits")
data class ProduitEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val quantity: Int
)
