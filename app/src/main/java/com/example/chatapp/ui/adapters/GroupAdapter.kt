package com.example.chatapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.data.models.Group
import com.example.chatapp.databinding.ItemGroupBinding

class GroupAdapter : RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {

    inner class GroupViewHolder(private val binding: ItemGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind() {
            val d = models[adapterPosition]
            binding.tvGroupName.text = d.name
            binding.tvLastMessage.text = "${d.name}: Qalay aman saw ne qv?"
        }

        init {
            binding.cardView.setOnClickListener {
                onItemClick?.invoke(models[adapterPosition])
            }
        }
    }

    var models = listOf<Group>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        return GroupViewHolder(
            ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind()
    }

    private var onItemClick: ((Group) -> Unit)? = null
    fun setOnItemClickListener(onItemClick: ((Group) -> Unit)) {
        this.onItemClick = onItemClick
    }
}