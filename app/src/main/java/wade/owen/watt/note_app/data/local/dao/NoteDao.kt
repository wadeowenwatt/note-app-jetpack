package wade.owen.watt.note_app.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import wade.owen.watt.note_app.data.model.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getNotes(): List<NoteEntity>

    @Query("SELECT * FROM note WHERE title LIKE :searchQuery")
    fun searchNote(searchQuery: String): List<NoteEntity>
}