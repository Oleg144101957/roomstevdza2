package com.vishnevskiypro.roomstevdza2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vishnevskiypro.roomstevdza2.model.Note


@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun readAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

}