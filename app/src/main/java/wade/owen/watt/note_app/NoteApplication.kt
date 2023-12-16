package wade.owen.watt.note_app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import wade.owen.watt.note_app.data.local.db.NoteDatabase

@HiltAndroidApp
class NoteApplication : Application() {
    val noteDatabase: NoteDatabase by lazy { NoteDatabase.getNotes(this) }

}