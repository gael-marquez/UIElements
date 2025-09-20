package com.example.uielements.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uielements.databinding.ItemSimpleBinding

class SimpleItemAdapter(
    private val items: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<SimpleItemAdapter.VH>() {

    inner class VH(private val binding: ItemSimpleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String) {
            binding.textItem.text = text
            binding.root.setOnClickListener { onClick(text) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemSimpleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size
}