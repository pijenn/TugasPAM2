package com.example.praktikum2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResultAdapter(private val results: MutableList<CalculationResult>) :
    RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvResultItem: TextView = itemView.findViewById(R.id.tvResultItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result, parent, false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.tvResultItem.text = results[position].resultText
    }

    override fun getItemCount(): Int = results.size

    fun addResult(result: CalculationResult) {
        results.add(0, result) // Tambahkan ke posisi pertama
        notifyItemInserted(0)
    }
}
