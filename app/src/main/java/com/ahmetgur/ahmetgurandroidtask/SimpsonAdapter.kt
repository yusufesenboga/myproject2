package com.ahmetgur.ahmetgurandroidtask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmetgur.ahmetgurandroidtask.databinding.ItemSimpsonBinding
import com.ahmetgur.test1.RelatedTopic

class SimpsonAdapter : RecyclerView.Adapter<SimpsonAdapter.SimpsonViewHolder>() {

    inner class SimpsonViewHolder(val binding: ItemSimpsonBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCalback = object : DiffUtil.ItemCallback<RelatedTopic>() {
        override fun areItemsTheSame(oldItem: RelatedTopic, newItem: RelatedTopic): Boolean {
            return oldItem.Result == newItem.Result
        }

        override fun areContentsTheSame(oldItem: RelatedTopic, newItem: RelatedTopic): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCalback)
    var simpsons: List<RelatedTopic>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount() = simpsons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpsonViewHolder {
        return SimpsonViewHolder(
            ItemSimpsonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SimpsonViewHolder, position: Int) {
            holder.binding.apply {
                val simpson = simpsons[position]
                val title = simpson.Text.split(" -")
                tvTitle.text = title[0]
            }
    }


}