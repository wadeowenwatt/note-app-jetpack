package wade.owen.watt.note_app.ui.screen.sign_up

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val signUpRoute = "sign_up_route"

fun NavController.navigateToSignUp(
    navOptions: NavOptions? = null
) {
    this.navigate(signUpRoute, navOptions)
}

fun NavGraphBuilder.signUpScreen(
    navToHome: () -> Unit
) {
    composable(signUpRoute) {
        SignUpScreen()
    }
}