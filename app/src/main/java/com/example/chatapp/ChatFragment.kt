package com.example.chatapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chatapp.databinding.FragmentChatBinding

class ChatFragment: Fragment(R.layout.fragment_chat) {
    private lateinit var binding: FragmentChatBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentChatBinding.bind(view)


    }
}