package com.example.dailyforecast.forecast.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dailyforecast.databinding.ActivityForecastBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}