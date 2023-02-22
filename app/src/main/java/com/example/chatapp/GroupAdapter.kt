package com.example.chatapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.data.Group
import com.example.chatapp.databinding.ItemGroupBinding

class GroupAdapter : RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {

    inner class GroupViewHolder(private val binding: ItemGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(group: Group) {
            binding.apply {
                avatarName.text = group.group
                chatMessage.text = group.message
                time.text = group.date
                cardView.setOnClickListener {
                    onItemClick.invoke(group.id, adapterPosition)
                }
            }
        }
    }

    var models = listOf<Group>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        val binding = ItemGroupBinding.bind(v)
        return GroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind(models[position])
    }

    private var onItemClick: (id: Int, position: Int) -> Unit = { _, _ -> }
    fun setOnItemClickListener(onItemClick: (id: Int, position: Int) -> Unit) {
        this.onItemClick = onItemClick
    }
}