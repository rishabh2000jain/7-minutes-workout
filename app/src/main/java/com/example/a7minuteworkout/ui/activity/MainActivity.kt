package com.example.a7minuteworkout.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minuteworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startBtnFrameLayout.setOnClickListener {
           val intent = Intent(this@MainActivity, ExerciseActivity::class.java)
           startActivity(intent)
        }
        binding.historyLinearLayout.setOnClickListener {
           val intent = Intent(this@MainActivity, HistoryActivity::class.java)
           startActivity(intent)
        }
        binding.bmiBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, BmiActivity::class.java)
            startActivity(intent)
        }
    }


}