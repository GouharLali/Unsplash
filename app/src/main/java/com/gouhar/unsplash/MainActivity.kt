package com.gouhar.unsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.fueled.reclaim.ItemsViewAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(
            MoshiConverterFactory.create(Moshi.Builder().build())   // telling retrofit to use MOSHI.
        )
        .build()

    private val photoApi: PhotosApi = retrofit.create()

    private val itemAdapter = ItemsViewAdapter() // Created ItemsAdapter - Contains the multiple adapters.

    private val recyclerView by lazy {
         findViewById<RecyclerView>(R.id.recycler_view)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
         recyclerView.apply{
             adapter = itemAdapter
         }


        lifecycleScope.launch {
            try {
                val photos = photoApi.getPhotos(page = 1)   //Photos we got. Adding it to ItemAdapter

                itemAdapter.replaceItems(photos.map { photo ->  PhotoAdapterItem(photo) })
            // Taking our list of photos // converting it PhotoAdapterIem then adding it itemsAdapter
            } catch (e: Exception) {  // Catching the error.
                e.printStackTrace()
            }
        }
    }
}