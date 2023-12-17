package wade.owen.watt.note_app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import wade.owen.watt.note_app.data.model.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNoteDetail(id: Int): Flow<NoteEntity>

    @Query("SELECT * FROM note WHERE title LIKE :searchQuery")
    fun searchNote(searchQuery: String): Flow<List<NoteEntity>>
}