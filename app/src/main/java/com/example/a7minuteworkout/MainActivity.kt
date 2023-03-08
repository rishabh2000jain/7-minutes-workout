package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.a7minuteworkout.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startBtnFrameLayout.setOnClickListener {
           val intent = Intent(this@MainActivity,ExerciseActivity::class.java)
           startActivity(intent)
        }
        binding.bmiBtn.setOnClickListener {
            val intent = Intent(this@MainActivity,BmiActivity::class.java)
            startActivity(intent)
        }
    }


}