package com.example.a7minuteworkout.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.a7minuteworkout.util.BMIIndexUtil
import com.example.a7minuteworkout.databinding.FragmentUSUnitBinding
import kotlin.math.pow


class USUnitFragment : Fragment() {
    lateinit var binding:FragmentUSUnitBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUSUnitBinding.inflate(layoutInflater)

        binding.calculateBtn.setOnClickListener {
            if(hasEmptyFields()){
                Toast.makeText(context,"Input Fields can not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val heightInInch = binding.heightInchEdt.text.toString().toInt()
            val heightInFeet = binding.heightFeetEdt.text.toString().toInt()
            val weightInPounds = binding.weightTextView.text.toString().toDouble()
            val heightInInchTotal = heightInInch+(heightInFeet*12.0)
            val bmiIndex = 703.0*(weightInPounds/ heightInInchTotal.pow(2))
            makeBmiValuesVisible()
            binding.bmiIndexTxt.text = bmiIndex.format(2)
            val bmiCategory = BMIIndexUtil.getBMICategory(bmiIndex)
            binding.bmiCategoryTxt.text = bmiCategory
            binding.bmiCategoryDescTxt.text = BMIIndexUtil.getBMIDescFromCategory(bmiCategory)
        }
        return binding.root
    }

    private fun makeBmiValuesVisible(){
        binding.bmiIndexTxt.visibility = View.VISIBLE
        binding.bmiCategoryTxt.visibility = View.VISIBLE
        binding.bmiCategoryDescTxt.visibility = View.VISIBLE
    }
    private fun hasEmptyFields():Boolean{
        return listOf(binding.weightTextView.text.toString(),binding.heightInchEdt.text.toString(),binding.heightFeetEdt.text.toString()).any { it.isEmpty() }
    }

}
fun Double.format(digits: Int) = "%.${digits}f".format(this)