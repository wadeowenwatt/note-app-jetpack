package wade.owen.watt.note_app.ui.screen.home

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val homeRoute = "home_route"

fun NavController.navigateHome(
    navOptions: NavOptions? = null
) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(navigateToNoteDetail: (Int?) -> Unit, navigateToSignIn: () -> Unit) {
    composable(homeRoute) {
        HomeScreen(
            hiltViewModel<HomeViewModel>(),
            navigateToNoteDetail = {
                navigateToNoteDetail.invoke(it)
            },
            navigateToSignIn = {
                navigateToSignIn.invoke()
            }
        )
    }
}