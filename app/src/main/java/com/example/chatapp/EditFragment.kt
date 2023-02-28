package com.example.chatapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chatapp.data.local.LocalStorage
import com.example.chatapp.databinding.FragmentEditBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks

class EditFragment : Fragment(R.layout.fragment_edit) {
    private lateinit var binding: FragmentEditBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEditBinding.bind(view)

        binding.icDone.clicks().debounce(200).onEach {
            val name = binding.etName.text.toString()
            if (name.isNotEmpty()) {
                LocalStorage().username = name
                Snackbar.make(
                    requireView(), "User name has been successfully updated.", Snackbar.LENGTH_SHORT
                ).show()
                findNavController().popBackStack()
            }
        }.launchIn(lifecycleScope)

        binding.icBack.clicks().debounce(200).onEach {
            findNavController().popBackStack()
        }.launchIn(lifecycleScope)

    }
}