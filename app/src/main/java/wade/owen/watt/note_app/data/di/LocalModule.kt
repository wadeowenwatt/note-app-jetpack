package wade.owen.watt.note_app.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import wade.owen.watt.note_app.data.local.dao.NoteDao
import wade.owen.watt.note_app.data.local.db.NoteDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ): NoteDatabase {
        return NoteDatabase.getNotes(context)
    }

    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao()
    }
}