package wade.owen.watt.note_app.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import wade.owen.watt.note_app.R
import wade.owen.watt.note_app.ui.screen.calendar.calendarRoute
import wade.owen.watt.note_app.ui.screen.home.homeRoute

enum class BottomNav(
    val route: String,
    @DrawableRes val iconId: Int,
    @StringRes val titleTextId: Int,
) {
    CALENDAR(calendarRoute, R.drawable.ic_home, R.string.home),
    HOME(homeRoute, R.drawable.ic_home, R.string.home),
}