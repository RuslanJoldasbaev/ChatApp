package com.example.chatapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chatapp.data.models.Group
import com.example.chatapp.databinding.DialogGroupAddBinding
import com.example.chatapp.presentation.chat.ChatViewModel
import com.example.chatapp.presentation.group.AddGroupViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AddGroupDialog : DialogFragment(R.layout.dialog_group_add) {
    private lateinit var binding: DialogGroupAddBinding
    private lateinit var viewModel: AddGroupViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogGroupAddBinding.bind(view)
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[AddGroupViewModel::class.java]
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.icDone.setOnClickListener {
            val name = binding.etName.text.toString()
            if (name.isNotEmpty()) {
                lifecycleScope.launchWhenResumed {
                    viewModel.addGroup(name)
                }
            } else {
                Snackbar.make(
                    requireView(), "Please enter name of your group!", Snackbar.LENGTH_SHORT
                ).show()
            }

            binding.icBack.setOnClickListener {
                findNavController().popBackStack()
            }

        }
    }

    private fun initObservers() {
        viewModel.addGroupSuccesFlow.onEach {
            Snackbar.make(
                requireView(), "Group was succesfully created.", Snackbar.LENGTH_SHORT
            ).show()
            findNavController().popBackStack()
        }.launchIn(lifecycleScope)
    }

}