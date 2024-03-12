package wade.owen.watt.note_app.ui.screen.sign_in

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import wade.owen.watt.note_app.R
import wade.owen.watt.note_app.ui.screen.sign_in.compose_func.rememberFirebaseAuthLauncher
import wade.owen.watt.note_app.ui.theme.NoteAppTheme


@Composable
fun GoogleLoginButton(viewModel: SignInViewModel, navigateToHome: () -> Unit) {
    val context = LocalContext.current
    val launcherGg = rememberFirebaseAuthLauncher(
        onAuthComplete = {
            navigateToHome()
        },
        onAuthError = {}
    )
    Image(
        painter = painterResource(id = R.drawable.ic_google),
        contentDescription = "ic_gg",
        modifier = Modifier.clickable {
            viewModel.loginWithGoogle(context = context, launcher = launcherGg)
        },
    )
}

@Composable
fun FacebookLoginButton(viewModel: SignInViewModel, navigateToHome: () -> Unit) {
    val callbackManager = remember {
        CallbackManager.Factory.create()
    }
    val launcherFb = rememberLauncherForActivityResult(
        contract = viewModel.loginManager.createLogInActivityResultContract(
            callbackManager = callbackManager,
            loggerID = null
        )
    ) {
        // nothing to do. Handled in FacebookCallback
    }

    val scope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        viewModel.loginManager.registerCallback(
            callbackManager,
            callback = object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    Log.d("FACEBOOK", ">>>>>>> Cancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d("FACEBOOK", ">>>>>>> Error: ${error}")
                }

                override fun onSuccess(result: LoginResult) {
                    Log.d("FACEBOOK", ">>>>>>> Success: ${result}")
                    scope.launch {
                        val token = result.accessToken.token
                        val credential = FacebookAuthProvider.getCredential(token)
                        Firebase.auth.signInWithCredential(credential)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val user = Firebase.auth.currentUser
                                    navigateToHome()
                                }
                            }
                    }
                }
            })
        onDispose {
            viewModel.loginManager.unregisterCallback(callbackManager)
        }
    }

    Image(
        painter = painterResource(id = R.drawable.ic_facebook),
        contentDescription = "ic_fb",
        modifier = Modifier.clickable {
            viewModel.loginWithFacebook(launcherFb)
        },
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignInScreen(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    val viewModel = hiltViewModel<SignInViewModel>()
    val uiState = viewModel.uiState.collectAsState()


    val context = LocalContext.current
    NoteAppTheme(darkTheme = false) {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.app_icon),
                        contentDescription = "app_ic",
                        Modifier
                            .width(100.dp)
                            .height(100.dp)
                    )
                    TextField(
                        value = uiState.value.username ?: "",
                        onValueChange = {
                            viewModel.onChangedUsername(it)
                        },
                    )
                    TextField(
                        value = uiState.value.password ?: "",
                        onValueChange = {
                            viewModel.onChangedPassword(it)
                        },
                    )
                    Row {
                        FacebookLoginButton(viewModel = viewModel, navigateToHome)
                        Image(
                            painter = painterResource(id = R.drawable.ic_x),
                            contentDescription = "ic_x",
                            modifier = Modifier.clickable {

                            },
                        )
                        GoogleLoginButton(viewModel = viewModel, navigateToHome)
                    }
                    TextButton(onClick = { Firebase.auth.signOut() }) {
                        Text(text = "Sign out")
                    }
                    TextButton(onClick = {
                        Log.d("linhtn1", "${Firebase.auth.currentUser}")
                    }) {
                        Text(text = "Test")
                    }
                }
            }
        }
    }
}