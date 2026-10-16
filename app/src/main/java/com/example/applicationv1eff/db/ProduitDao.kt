package com.example.applicationv1eff.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.applicationv1eff.model.Produit

@Dao
interface ProduitDao {
    @Insert
    suspend fun insertProduit(produit: ProduitEntity)

    @Query("SELECT * FROM produits")
    suspend fun getAllProduits(): List<ProduitEntity>
}
