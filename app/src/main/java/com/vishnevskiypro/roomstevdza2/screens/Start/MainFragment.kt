package com.vishnevskiypro.roomstevdza2.screens.Start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishnevskiypro.roomstevdza2.R
import com.vishnevskiypro.roomstevdza2.databinding.FragmentMainBinding
import com.vishnevskiypro.roomstevdza2.viewmodel.NoteViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var recycler: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        noteAdapter = NoteAdapter()
        recycler = binding.recyclerView
        recycler.adapter = noteAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        mViewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            noteAdapter.setAdapter(note)
        })

        binding.addNote.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }

        return binding.root
    }

}