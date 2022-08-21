package com.vishnevskiypro.roomstevdza2.screens.Add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vishnevskiypro.roomstevdza2.R
import com.vishnevskiypro.roomstevdza2.databinding.FragmentAddBinding
import com.vishnevskiypro.roomstevdza2.model.Note
import com.vishnevskiypro.roomstevdza2.viewmodel.NoteViewModel

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var mViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.save.setOnClickListener {
            insertNoteToDatabase()
        }

        return binding.root
    }

    private fun insertNoteToDatabase(){

        val title = binding.noteTitle.text.toString()
        val description = binding.description.text.toString()

        if (inputCheck(title, description)){

            //Create obj
            val note = Note(id = 0, title = title, description = description)

            //add to db
            mViewModel.addNote(note)
            Toast.makeText(requireContext(), "Note added", Toast.LENGTH_LONG).show()

            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_mainFragment)

        } else {
            Toast.makeText(requireContext(), "Fill all fields please", Toast.LENGTH_LONG).show()

        }
    }

    private fun inputCheck(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }



}