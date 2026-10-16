package com.example.applicationv1eff.network

import com.example.applicationv1eff.model.Login
import com.example.applicationv1eff.model.Produit
import com.example.applicationv1eff.model.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(@Body loginData: Login): Call<Response>

    @GET("products")
    fun getProducts(): Call<List<Produit>>
}
