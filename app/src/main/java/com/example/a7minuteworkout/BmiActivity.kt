package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withCreated
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.a7minuteworkout.databinding.ActivityBmiBinding
import com.example.a7minuteworkout.fragments.MetricUnitFragment
import com.example.a7minuteworkout.fragments.USUnitFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BmiActivity : AppCompatActivity() {
    lateinit var binding:ActivityBmiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
     supportFragmentManager.beginTransaction().replace(R.id.fragmentHost,MetricUnitFragment()).commit()
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
            supportFragmentManager.beginTransaction().replace(R.id.fragmentHost,MetricUnitFragment()).commit()
        }

        binding.metricUnitRadioBtm.setOnClickListener {
            binding.bmiRadioBtn.isSelected = !binding.bmiRadioBtn.isSelected
            binding.metricUnitRadioBtm.isSelected = !binding.metricUnitRadioBtm.isSelected
            supportFragmentManager.beginTransaction().replace(R.id.fragmentHost,USUnitFragment()).commit()
        }
    }
}