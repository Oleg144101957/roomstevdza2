package com.vishnevskiypro.roomstevdza2.repository

import androidx.lifecycle.LiveData
import com.vishnevskiypro.roomstevdza2.data.NoteDao
import com.vishnevskiypro.roomstevdza2.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    val readAllNotes: LiveData<List<Note>> = noteDao.readAllNotes()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }
}