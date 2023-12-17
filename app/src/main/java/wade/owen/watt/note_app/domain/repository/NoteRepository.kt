package wade.owen.watt.note_app.domain.repository

import kotlinx.coroutines.flow.Flow
import wade.owen.watt.note_app.data.model.NoteEntity

interface NoteRepository {
    /* Retrieve all the notes */
    fun getAllNotes(): Flow<List<NoteEntity>>

    /* Retrieve a note with [id] */
    fun getNote(id: Int): Flow<NoteEntity>

    /* Insert note */
    suspend fun insertNote(note: NoteEntity)

    suspend fun deleteNote(note: NoteEntity)

    suspend fun updateNote(note: NoteEntity)

    fun searchNotes(query: String): Flow<List<NoteEntity>>
}