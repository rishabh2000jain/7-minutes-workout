package com.example.a7minuteworkout.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.a7minuteworkout.adapters.HistoryListAdapter
import com.example.a7minuteworkout.application.SevenMinutesApplication
import com.example.a7minuteworkout.databinding.ActivityHistoryBinding
import com.example.a7minuteworkout.db.History
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var historyListAdapter: HistoryListAdapter? = null
    lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = "History"

        }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        setupRecyclerView()
        getHistoryData()
    }

    private fun setupRecyclerView() {
        historyListAdapter = HistoryListAdapter(ArrayList())
        binding.historyListView.adapter = historyListAdapter
    }

    private fun getHistoryData(){
        val dao = (application as SevenMinutesApplication).database.getDao()
        lifecycleScope.launch{
            dao.getHistoryList().collect{
                historyListAdapter?.updateList(it)
            }
        }
    }
}