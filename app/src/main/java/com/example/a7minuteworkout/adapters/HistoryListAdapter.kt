package com.example.a7minuteworkout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.databinding.HistoryListItemBinding
import com.example.a7minuteworkout.db.History
import java.text.SimpleDateFormat
import java.util.Date

class HistoryListAdapter(private var historyList:List<History>):RecyclerView.Adapter<HistoryListAdapter.HistoryListViewAdapter>() {
    private val simpleDateFormat:SimpleDateFormat = SimpleDateFormat("dd MMM yyyy hh:mm a")
    class HistoryListViewAdapter(val binding: HistoryListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListViewAdapter {
        val binding = HistoryListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HistoryListViewAdapter(binding)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: HistoryListViewAdapter, position: Int) {
        holder.binding.historyDateTxt.text = simpleDateFormat.format(Date(historyList[position].date))

    }

    fun updateList(list:List<History>){
        this.historyList = list
        notifyDataSetChanged()
    }

}