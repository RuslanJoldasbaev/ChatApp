package com.example.chatapp.ui.chat

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.chatapp.R
import com.example.chatapp.data.models.MessageData
import com.example.chatapp.databinding.FragmentChatBinding
import com.example.chatapp.presentation.chat.ChatViewModel
import com.example.chatapp.ui.adapters.ChatAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks

class ChatFragment : Fragment(R.layout.fragment_chat) {
    private lateinit var binding: FragmentChatBinding
    private val args: ChatFragmentArgs by navArgs()
    private var _adapter: ChatAdapter? = null
    private val adapter get() = _adapter!!

    private lateinit var viewModel: ChatViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentChatBinding.bind(view)

        initData()
        initObservers()
        initListeners()

        binding.apply {
            arrBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    private fun initData() {
        binding.groupName.text = args.groupName
        _adapter = ChatAdapter()
        binding.recyclerViewChat.adapter = adapter

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[ChatViewModel::class.java]
    }

    private fun initObservers() {
        FirebaseDatabase.getInstance().getReference(args.groupId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    try {
                        val list = mutableListOf<MessageData>()
                        snapshot.children.forEach {
                            val item = it.value as HashMap<*, *>
                            list.add(
                                MessageData(
                                    item["message"].toString(),
                                    item["user"].toString(),
                                    item["time"].toString()
                                )
                            )
                        }
                        adapter.submitList(list)
                        if (list.isNotEmpty()) {
                            binding.recyclerViewChat.smoothScrollToPosition(list.lastIndex)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }

    private fun initListeners() {

        binding.etMessage.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                binding.icAttach.visibility = View.GONE
                binding.icVideo.visibility = View.GONE
                binding.icSend.visibility = View.VISIBLE
            } else {
                binding.icAttach.visibility = View.VISIBLE
                binding.icVideo.visibility = View.VISIBLE
                binding.icSend.visibility = View.GONE
            }
        }

        binding.icSend.clicks().debounce(200).onEach {
            val message = binding.etMessage.text.toString()
            if (it.toString().isNotEmpty()) {
                viewModel.sendMessage(message, args.groupId)
                binding.etMessage.setText("")
            } else {
                binding.icAttach.visibility = View.VISIBLE
                binding.icVideo.visibility = View.VISIBLE
                binding.icSend.visibility = View.GONE
            }
        }.launchIn(lifecycleScope)

        binding.arrBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
