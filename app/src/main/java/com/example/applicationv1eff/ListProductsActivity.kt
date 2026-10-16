package com.example.applicationv1eff

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationv1eff.adapter.MyAdapter
import com.example.applicationv1eff.db.AppDatabase
import com.example.applicationv1eff.db.ProduitEntity
import com.example.applicationv1eff.model.Produit
import com.example.applicationv1eff.model.Produit.Companion.lstProducts
import com.example.applicationv1eff.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListProductsActivity : AppCompatActivity() {

    private lateinit var productsView: RecyclerView
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_products)

        productsView = findViewById(R.id.productsView)
        productsView.layoutManager = LinearLayoutManager(this)

        fetchProducts() // 🔁 Appel de l'API pour récupérer les produits
    }

    private fun fetchProducts() {
        val call = RetrofitInstance.apiService.getProducts()

        call.enqueue(object : Callback<List<Produit>> {
            override fun onResponse(call: Call<List<Produit>>, response: Response<List<Produit>>) {
                val produits = response.body()
                if (response.isSuccessful && produits != null) {
                    lstProducts.clear()
                    lstProducts.addAll(produits)

                    // 🔄 Création et assignation de l'adapter
                    myAdapter = MyAdapter(lstProducts) { produit ->
                        confirmSave(produit)
                    }
                    productsView.adapter = myAdapter
                } else {
                    Toast.makeText(this@ListProductsActivity, "Erreur de données", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Produit>>, t: Throwable) {
                Toast.makeText(this@ListProductsActivity, "Erreur lors du chargement : ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun confirmSave(produit: Produit) {
        AlertDialog.Builder(this)
            .setTitle("Confirmation")
            .setMessage("Êtes-vous sûr de vouloir enregistrer ce produit ?")
            .setPositiveButton("Oui") { _, _ ->
                val db = AppDatabase.getDatabase(this)
                val produitEntity = ProduitEntity(
                    id = produit.id,
                    name = produit.name,
                    quantity = produit.quantity
                )

                CoroutineScope(Dispatchers.IO).launch {
                    db.produitDao().insertProduit(produitEntity)
                    runOnUiThread {
                        Toast.makeText(this@ListProductsActivity, "Produit enregistré avec succès", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Non", null)
            .show()
    }
}
