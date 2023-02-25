package com.example.chatapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.data.models.Group
import com.example.chatapp.databinding.DialogGroupAddBinding
import com.example.chatapp.presentation.chat.ChatViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

class AddGroupDialog : DialogFragment(R.layout.dialog_group_add){
    private lateinit var binding: DialogGroupAddBinding
    private lateinit var viewModel: ChatViewModel


    val getAllGroupFlow =MutableSharedFlow<List<Group>>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogGroupAddBinding.bind(view)
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(ChatViewModel::class.java)
    }

}