package com.example.a7minuteworkout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.a7minuteworkout.BMIIndexUtil
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.databinding.FragmentMetricUnitBinding
import com.example.a7minuteworkout.databinding.FragmentUSUnitBinding
import kotlin.math.pow

class MetricUnitFragment : Fragment() {
    lateinit var binding: FragmentMetricUnitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMetricUnitBinding.inflate(layoutInflater)

        binding.calculateBtn.setOnClickListener {
            if(hasEmptyFields()){
                Toast.makeText(context,"Input Fields can not be empty",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val heightInCm = binding.heightTextView.text.toString().toDouble()
            val weightInKG = binding.weightTextView.text.toString().toDouble()
            val heightInMeter = heightInCm / 100
            val bmiIndex = (weightInKG / heightInMeter.pow(2))
            makeBmiValuesVisible()
            binding.bmiIndexTxt.text = bmiIndex.format(2)
            val bmiCategory = BMIIndexUtil.getBMICategory(bmiIndex)
            binding.bmiCategoryTxt.text = bmiCategory
            binding.bmiCategoryDescTxt.text = BMIIndexUtil.getBMIDescFromCategory(bmiCategory)
        }
        return binding.root
    }

    private fun hasEmptyFields():Boolean{
        return listOf<String>(binding.weightTextView.text.toString(),binding.heightTextView.text.toString()).any { it.isEmpty() }
    }

    private fun makeBmiValuesVisible(){
        binding.bmiIndexTxt.visibility = View.VISIBLE
        binding.bmiCategoryTxt.visibility = View.VISIBLE
        binding.bmiCategoryDescTxt.visibility = View.VISIBLE
    }
}