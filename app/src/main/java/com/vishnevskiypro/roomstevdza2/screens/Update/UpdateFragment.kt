package com.vishnevskiypro.roomstevdza2.screens.Update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vishnevskiypro.roomstevdza2.R
import com.vishnevskiypro.roomstevdza2.databinding.FragmentUpdateBinding
import com.vishnevskiypro.roomstevdza2.model.Note
import com.vishnevskiypro.roomstevdza2.viewmodel.NoteViewModel

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        binding = FragmentUpdateBinding.inflate(layoutInflater, container, false)

        binding.noteTitle.setText(args.currentNote.title)
        binding.description.setText(args.currentNote.description)

        binding.save.setOnClickListener {
            updateNote()
        }

        return binding.root
    }

    private fun updateNote(){
        val title =  binding.noteTitle.text.toString()
        val description = binding.description.text.toString()

        if (inputCheck(title = title, description = description)){

            //Создаем объект Note
            val updatedNote = Note(args.currentNote.id, title, description)

            //Update Current User
            mViewModel.updateNote(updatedNote)
            Toast.makeText(requireContext(), "Note Updatede", Toast.LENGTH_LONG).show()

            //Navigate
            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
        } else {
            Toast.makeText(requireContext(), "Fill all fields please", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(title: String, description: String):Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }
}