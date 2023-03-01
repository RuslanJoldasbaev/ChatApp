package com.example.chatapp.ui.groups

import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentGroupBinding
import com.example.chatapp.presentation.group.GroupViewModel
import com.example.chatapp.ui.adapters.GroupAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class GroupFragment : Fragment(R.layout.fragment_group) {
    private lateinit var binding: FragmentGroupBinding
    private lateinit var viewModel: GroupViewModel
    private var _adapter: GroupAdapter? = null
    private val adapter get() = _adapter!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGroupBinding.bind(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[GroupViewModel::class.java]

        initData()
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        adapter.setOnItemClickListener {
            findNavController().navigate(
                GroupFragmentDirections.actionGroupFragment2ToChatFragment2(
                    it.id, it.name
                )
            )
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(GroupFragmentDirections.actionGroupFragment2ToAddGroupDialog())
        }

        binding.settingButton.setOnClickListener {
            findNavController().navigate(GroupFragmentDirections.actionGroupFragment2ToEditFragment())
        }
    }

    private fun initObservers() {
        viewModel.getGroupChatsFlow.onEach {
            adapter.models = it
        }.launchIn(lifecycleScope)
    }

    private fun initData() {
        _adapter = GroupAdapter()
        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenResumed {
            viewModel.getGroupChats()
        }
    }
}