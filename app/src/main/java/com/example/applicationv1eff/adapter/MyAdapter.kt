package com.example.applicationv1eff.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationv1eff.R
import com.example.applicationv1eff.model.Produit

class MyAdapter(
    private val produits: List<Produit>,
    private val onItemClick: (Produit) -> Unit
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.findViewById<TextView>(R.id.txtName)
        val txtQuantity: TextView = itemView.findViewById(R.id.txtQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val produit = produits[position]
        holder.txtName.text = produit.name
        holder.txtQuantity.text = "Quantité: ${produit.quantity}"
        holder.itemView.setOnClickListener { onItemClick(produit) }
    }

    override fun getItemCount(): Int = produits.size
}