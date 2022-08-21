package com.vishnevskiypro.roomstevdza2.screens.Start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vishnevskiypro.roomstevdza2.databinding.ItemNoteScreenBinding
import com.vishnevskiypro.roomstevdza2.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: ItemNoteScreenBinding) : RecyclerView.ViewHolder(binding.root)

    var listOfNotes = emptyList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNoteScreenBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val note = listOfNotes[position]

        holder.binding.apply {
            noteId.text = note.id.toString()
            noteTitle.text = note.title
        }


        //Обрабатываем клик по элементу в RecyclerView
        holder.binding.noteLayout.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToUpdateFragment(note)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = listOfNotes.size

    fun setAdapter(list: List<Note>){
        listOfNotes = list
        notifyDataSetChanged()
    }
}