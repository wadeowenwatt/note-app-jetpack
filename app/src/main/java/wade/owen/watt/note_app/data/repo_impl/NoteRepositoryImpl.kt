package wade.owen.watt.note_app.data.repo_impl

import kotlinx.coroutines.flow.Flow
import wade.owen.watt.note_app.data.local.dao.NoteDao
import wade.owen.watt.note_app.data.model.NoteEntity
import wade.owen.watt.note_app.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao): NoteRepository {
    override fun getAllNotes(): Flow<List<NoteEntity>> {
        return noteDao.getNotes()
    }

    override fun getNote(id: Int): Flow<NoteEntity> {
        return noteDao.getNoteDetail(id)
    }

    override suspend fun insertNote(note: NoteEntity) {
        noteDao.insert(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        noteDao.delete(note)
    }

    override suspend fun updateNote(note: NoteEntity) {
        noteDao.update(note)
    }

    override fun searchNotes(query: String): Flow<List<NoteEntity>> {
        return noteDao.searchNote(query)
    }
}