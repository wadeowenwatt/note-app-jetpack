package wade.owen.watt.note_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import wade.owen.watt.note_app.ui.screen.home.homeRoute
import wade.owen.watt.note_app.ui.screen.home.homeScreen
import wade.owen.watt.note_app.ui.screen.note_detail.navigateNoteDetail
import wade.owen.watt.note_app.ui.screen.note_detail.noteDetail
import wade.owen.watt.note_app.ui.screen.note_detail.noteDetailScreen
import wade.owen.watt.note_app.ui.theme.NoteAppTheme

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    NoteAppTheme(darkTheme = true) {
        NavHost(navController = navController, startDestination = homeRoute) {
            homeScreen {
                navController.navigateNoteDetail(it)
            }
            noteDetailScreen { navController.popBackStack() }
        }
    }
}