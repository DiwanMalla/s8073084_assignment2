package com.example.s8073084_assignment2.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.s8073084_assignment2.data.Entity
import com.example.s8073084_assignment2.databinding.ItemEntityBinding

class DashboardAdapter(private val onItemClicked: (Entity) -> Unit) : ListAdapter<Entity, DashboardAdapter.EntityViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val binding = ItemEntityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EntityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = getItem(position)
        holder.bind(entity)
        holder.itemView.setOnClickListener {
            onItemClicked(entity)
        }
    }

    class EntityViewHolder(private val binding: ItemEntityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: Entity) {
            binding.titleText.text = entity.title
            binding.authorText.text = entity.author
            // The image is now set directly in the XML, so no Glide call is needed here.
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Entity>() {
            override fun areItemsTheSame(oldItem: Entity, newItem: Entity): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Entity, newItem: Entity): Boolean {
                return oldItem == newItem
            }
        }
    }
}