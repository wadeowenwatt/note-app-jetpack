package wade.owen.watt.note_app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Nonnull @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "content")
    val content: String,
)