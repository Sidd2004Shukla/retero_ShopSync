package com.example.reterofitapi

import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reterofitapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val reterofit= Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiinterface::class.java)
        val reterofitdata=reterofit.getproduct()
        reterofitdata.enqueue(object : Callback<mydata?> {
            override fun onResponse(p0: Call<mydata?>, p1: Response<mydata?>) {
            var responsebody=p1.body()

                val productlist=responsebody?.products
                val collectDetailsInSb=StringBuilder()
                for(product in productlist!!) {
                    collectDetailsInSb.append(product.title + " ")
                }

                binding.recycle.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
                binding.recycle.adapter=MyAdapter(this@MainActivity,productlist)



            }

            override fun onFailure(p0: Call<mydata?>, p1: Throwable) {
               Log.d("Main Activity","on failure"+p1.message)
            }
        })

    }
}