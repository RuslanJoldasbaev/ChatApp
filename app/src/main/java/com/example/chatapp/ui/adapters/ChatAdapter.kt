package com.example.chatapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.data.local.LocalStorage
import com.example.chatapp.data.models.MessageData
import com.example.chatapp.databinding.ItemChatReceiveBinding
import com.example.chatapp.databinding.ItemChatSendBinding

class ChatAdapter : ListAdapter<MessageData, RecyclerView.ViewHolder>(diffCall) {

    companion object {
        const val ME = 0
        const val ANOTHER = 1
    }

    inner class ChatAnotherViewHolder(private val binding: ItemChatReceiveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val d = getItem(absoluteAdapterPosition)
            binding.tvMessage.text = d.message
            binding.tvUsername.text = d.user
            binding.tvTime.text = d.time
        }
    }

    inner class ChatMeViewHolder(private val binding: ItemChatSendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val d = getItem(absoluteAdapterPosition)
            binding.tvMessage.text = d.message
            binding.tvTime.text = d.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ME -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_send, parent, false)
                val binding = ItemChatSendBinding.bind(view)
                ChatMeViewHolder(binding)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_receive, parent, false)
                val binding = ItemChatReceiveBinding.bind(view)
                ChatAnotherViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when (getItem(position).user) {
            LocalStorage().username -> ME
            else -> ANOTHER
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position).user) {
            LocalStorage().username -> {
                (holder as ChatMeViewHolder).bind()
            }
            else -> {
                (holder as ChatAnotherViewHolder).bind()
            }
        }
    }

    private object diffCall : DiffUtil.ItemCallback<MessageData>() {
        override fun areContentsTheSame(oldItem: MessageData, newItem: MessageData): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: MessageData, newItem: MessageData): Boolean {
            return oldItem.message == newItem.message && oldItem.user == newItem.user
        }
    }
}