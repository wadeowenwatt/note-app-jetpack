package wade.owen.watt.note_app.ui.screen.sign_in

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import wade.owen.watt.note_app.ui.screen.note_detail.NoteDetailScreen

const val signInRoute = "sign_in_route"

fun NavController.navigateToSignIn(
    navOptions: NavOptions? = null
) {
    this.navigate(signInRoute, navOptions)
}

fun NavGraphBuilder.signInScreen(navToHome: () -> Unit) {
    composable(signInRoute) {
        SignInScreen()
    }
}