package com.example.a7minuteworkout.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.databinding.ActivityBmiBinding
import com.example.a7minuteworkout.ui.fragments.MetricUnitFragment
import com.example.a7minuteworkout.ui.fragments.USUnitFragment

class BmiActivity : AppCompatActivity() {
    lateinit var binding:ActivityBmiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
     supportFragmentManager.beginTransaction().replace(R.id.fragmentHost, MetricUnitFragment()).commit()
        supportActionBar?.title = "BMI Calculator"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        handleTabClick()
    }

    private fun handleTabClick()
    {
        binding.bmiRadioBtn.setOnClickListener {
            binding.bmiRadioBtn.isSelected = !binding.bmiRadioBtn.isSelected
            binding.metricUnitRadioBtm.isSelected = !binding.metricUnitRadioBtm.isSelected
            supportFragmentManager.beginTransaction().replace(R.id.fragmentHost,
                MetricUnitFragment()
            ).commit()
        }

        binding.metricUnitRadioBtm.setOnClickListener {
            binding.bmiRadioBtn.isSelected = !binding.bmiRadioBtn.isSelected
            binding.metricUnitRadioBtm.isSelected = !binding.metricUnitRadioBtm.isSelected
            supportFragmentManager.beginTransaction().replace(R.id.fragmentHost, USUnitFragment()).commit()
        }
    }
}