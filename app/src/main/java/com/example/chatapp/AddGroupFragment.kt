package com.example.chatapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentGroupAddBinding
import com.example.chatapp.presentation.group.AddGroupViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ldralighieri.corbind.view.clicks

class AddGroupFragment : Fragment(R.layout.fragment_group_add) {
    private lateinit var binding: FragmentGroupAddBinding
    private val viewModel by viewModel<AddGroupViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGroupAddBinding.bind(view)

        initListeners()
        initObservers()

    }

    private fun initListeners() {
        binding.apply {
            icBack.clicks().debounce(200).onEach {
                findNavController().popBackStack()
            }.launchIn(lifecycleScope)

            icDone.setOnClickListener {
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