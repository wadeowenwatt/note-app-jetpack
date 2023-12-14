package wade.owen.watt.note_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import wade.owen.watt.note_app.screen.home.HomeScreen
import wade.owen.watt.note_app.screen.note_detail.NoteDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            HomeScreen()
            NoteDetailScreen()
        }
    }
}

