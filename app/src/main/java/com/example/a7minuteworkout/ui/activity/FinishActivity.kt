package com.example.a7minuteworkout.ui.activity

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minuteworkout.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {
    lateinit var binding: ActivityFinishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.finishBtn.setOnClickListener {
            finish()
        }
    }
}