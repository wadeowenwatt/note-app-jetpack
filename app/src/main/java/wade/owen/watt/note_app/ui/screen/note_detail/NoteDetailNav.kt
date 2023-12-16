package wade.owen.watt.note_app.ui.screen.note_detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val noteDetail = "note_detail_route"

fun NavController.navigateNoteDetail(
    navOptions: NavOptions? = null
) {
    this.navigate(noteDetail, navOptions)
}

fun NavGraphBuilder.noteDetailScreen(navToBack: () -> Unit) {
    composable(noteDetail) {
        NoteDetailScreen()
    }
}