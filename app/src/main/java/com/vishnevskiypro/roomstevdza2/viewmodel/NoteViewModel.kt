package com.vishnevskiypro.roomstevdza2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vishnevskiypro.roomstevdza2.data.NoteDatabase
import com.vishnevskiypro.roomstevdza2.repository.NoteRepository
import com.vishnevskiypro.roomstevdza2.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel (app: Application): AndroidViewModel(app) {

    var readAllData: LiveData<List<Note>>
    private val repository: NoteRepository

    init {
        val noteDao = NoteDatabase.getDatabase(app).noteDao()
        repository = NoteRepository(noteDao)
        readAllData = repository.readAllNotes
    }

    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateNote(note)
        }
    }


}