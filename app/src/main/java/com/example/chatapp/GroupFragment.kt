package com.example.chatapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chatapp.databinding.FragmentGroupBinding

class GroupFragment : Fragment(R.layout.fragment_group) {
    private lateinit var binding: FragmentGroupBinding
    private val adapter = GroupAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGroupBinding.bind(view)

        binding.apply {
            adapter.setOnItemClickListener { id, position ->
                val bundle = Bundle()
                bundle.putInt("id", id)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ChatFragment::class.java, bundle)
                    .addToBackStack(GroupFragment::class.java.simpleName)
                    .commit()
            }
        }

    }
}