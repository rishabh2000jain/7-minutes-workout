package com.example.a7minuteworkout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.databinding.ExerciseListItemBinding
import com.example.a7minuteworkout.models.ExerciseModel

class ExerciseListAdapter(private val exerciseList:ArrayList<ExerciseModel>) : RecyclerView.Adapter<ExerciseListAdapter.ExerciseListViewHolder>(){
    inner class ExerciseListViewHolder(val listItemBinding: ExerciseListItemBinding):RecyclerView.ViewHolder(listItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseListViewHolder {
        return ExerciseListViewHolder(ExerciseListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: ExerciseListViewHolder, position: Int) {
        val binding = holder.listItemBinding
        val context = binding.root.context
        binding.exerciseNumber.text = (position+1).toString()
        if(exerciseList[position].getIsCompleted()){
            binding.root.background = context.resources.getDrawable(R.drawable.circular_color_accent_background,context.theme)
            binding.exerciseNumber.setTextColor(context.resources.getColor(R.color.white,context.theme))
        }else if(exerciseList[position].getIsSelected()){
            binding.root.setBackgroundColor(context.resources.getColor(R.color.white,context.theme))
            binding.root.background = context.resources.getDrawable(R.drawable.circular_shape_with_acent_border_bg,context.theme)
            binding.exerciseNumber.setTextColor(context.resources.getColor(R.color.black,context.theme))
        }else{
            binding.root.background = context.resources.getDrawable(R.drawable.ciurcular_shape_bg,context.theme)
            binding.exerciseNumber.setTextColor(context.resources.getColor(R.color.black,context.theme))
        }
    }

}