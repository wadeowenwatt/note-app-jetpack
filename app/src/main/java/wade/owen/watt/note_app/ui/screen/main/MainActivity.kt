package wade.owen.watt.note_app.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import wade.owen.watt.note_app.ui.navigation.NavGraph
import wade.owen.watt.note_app.ui.screen.home.HomeScreen
import wade.owen.watt.note_app.ui.screen.note_detail.NoteDetailScreen
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavGraph()
        }
    }
}

