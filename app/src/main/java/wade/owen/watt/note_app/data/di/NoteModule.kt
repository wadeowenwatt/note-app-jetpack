package wade.owen.watt.note_app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import wade.owen.watt.note_app.data.local.dao.NoteDao
import wade.owen.watt.note_app.data.repo_impl.NoteRepositoryImpl
import wade.owen.watt.note_app.domain.repository.NoteRepository

@Module
@InstallIn(ViewModelComponent::class)
class NoteModule {
    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }
}