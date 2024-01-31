package com.example.weatherappanimation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherappanimation.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val weatherList = listOf(
            Weather("Orlando", "Sunny", 1, "25"),
            Weather("Nuuk", "Rainy", 3, "18"),
            Weather("Istanbul", "Partly Cloudy", 2, "22"),
            Weather("Toronto", "Snowy", 4, "15"),
            Weather("Istanbul", "Partly Cloudy", 2, "22"),
            Weather("Toronto", "Snowy", 4, "15"),
            Weather("Orlando", "Sunny", 1, "28"),
            Weather("Nuuk", "Rainy", 3, "20"),
            Weather("Istanbul", "Partly Cloudy", 2, "24"),
            Weather("Toronto", "Snowy", 4, "12")
        )

        val adapter = Adapter(
            onClickListener = {
                val intent = Intent(this@MainActivity2, SunnyActivity::class.java)
                intent.putExtra("clave_entero", it.state)
                startActivity(intent)
            }
        )
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        adapter.submitList(weatherList)

    }
}