package wade.owen.watt.note_app.ui.screen.note_detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val noteDetail = "note_detail_route?"

fun NavController.navigateNoteDetail(
    id: Int? = null,
    navOptions: NavOptions? = null
) {
    if (id == null) this.navigate(
        noteDetail,
        navOptions
    ) else this.navigate(noteDetail.plus("?id=${id}}"), navOptions)
}

fun NavGraphBuilder.noteDetailScreen(navToBack: () -> Unit) {
    composable(noteDetail.plus("?id={id}")) {backStackEntry ->
        val noteId = backStackEntry.arguments?.getInt("id")
        NoteDetailScreen(noteId)
    }
}