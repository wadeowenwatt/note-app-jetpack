package wade.owen.watt.note_app.ui.screen.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val splashRoute = "splash_route"

fun NavController.navigateSplash(
    navOptions: NavOptions? = null
) {
    this.navigate(splashRoute, navOptions)
}

fun NavGraphBuilder.splashScreen(navigateToLogin: () -> Unit, navigateToHome: () -> Unit) {
    composable(splashRoute) {
        AnimatedSplashScreen(
            navigateToHome = { navigateToHome.invoke() },
            navigateToLogin = { navigateToLogin.invoke() }
        )

    }
}