package wade.owen.watt.note_app.ui.screen.calendar

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val calendarRoute = "calendar_route"

fun NavController.navigateToCalendar(
    navOptions: NavOptions? = null
) {
    this.navigate(calendarRoute, navOptions = navOptions)
}

fun NavGraphBuilder.calendarScreen() {
    composable(calendarRoute) {

    }
}