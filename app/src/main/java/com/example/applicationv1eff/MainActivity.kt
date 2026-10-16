package com.example.applicationv1eff


import android.widget.*
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.applicationv1eff.R
import com.example.applicationv1eff.model.Login
import com.example.applicationv1eff.model.Response
import com.example.applicationv1eff.network.ApiService
import com.example.applicationv1eff.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response as RetrofitResponse

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtLogin = findViewById<EditText>(R.id.edtLogin)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val login = edtLogin.text.toString()
            val password = edtPassword.text.toString()

//            val api = RetrofitClient.instance.create(ApiService::class.java)
//            val call = api.login(Login(login, password))

            val call = RetrofitInstance.apiService.login(Login(login, password))

            call.enqueue(object : Callback<Response> {
                override fun onResponse(call: Call<Response>, response: RetrofitResponse<Response>) {
                    if (response.isSuccessful && response.body()?.access == true) {
                        startActivity(Intent(this@MainActivity, ListProductsActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@MainActivity, "Login ou mot de passe incorrect !", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Response>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Erreur réseau", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}