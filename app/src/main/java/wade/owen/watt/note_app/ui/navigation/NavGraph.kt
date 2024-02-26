package wade.owen.watt.note_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import wade.owen.watt.note_app.ui.screen.home.homeRoute
import wade.owen.watt.note_app.ui.screen.home.homeScreen
import wade.owen.watt.note_app.ui.screen.home.navigateHome
import wade.owen.watt.note_app.ui.screen.note_detail.navigateNoteDetail
import wade.owen.watt.note_app.ui.screen.note_detail.noteDetailScreen
import wade.owen.watt.note_app.ui.screen.sign_in.navigateToSignIn
import wade.owen.watt.note_app.ui.screen.sign_in.signInRoute
import wade.owen.watt.note_app.ui.screen.sign_in.signInScreen
import wade.owen.watt.note_app.ui.screen.sign_up.navigateToSignUp
import wade.owen.watt.note_app.ui.screen.sign_up.signUpRoute
import wade.owen.watt.note_app.ui.screen.sign_up.signUpScreen
import wade.owen.watt.note_app.ui.screen.splash.splashRoute
import wade.owen.watt.note_app.ui.screen.splash.splashScreen
import wade.owen.watt.note_app.ui.theme.NoteAppTheme

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    NoteAppTheme(darkTheme = true) {
        NavHost(navController = navController, startDestination = splashRoute) {
            splashScreen(
                navigateToHome = {
                    navController.navigateHome(
                        navOptions {
                            popUpTo(splashRoute) {
                                inclusive = true
                            }
                        }
                    )
                },
                navigateToLogin = { navController.navigateToSignIn() },
            )
            signInScreen(
                navToHome = {
                    navController.navigateHome(
                        navOptions {
                            popUpTo(signInRoute) {
                                inclusive = true
                            }
                        },
                    )
                },
                navToSignUp = {
                    navController.navigateToSignUp()
                }
            )
            signUpScreen { }
            homeScreen(
                navigateToNoteDetail = { navController.navigateNoteDetail(it) },
                navigateToSignIn = {
                    navController.navigateToSignIn(
                        navOptions {
                            popUpTo(homeRoute) {
                                inclusive = true
                            }
                        },
                    )
                },
            )
            noteDetailScreen { navController.popBackStack() }
        }
    }
}