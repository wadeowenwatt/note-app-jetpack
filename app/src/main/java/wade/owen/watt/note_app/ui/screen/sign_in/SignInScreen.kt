package wade.owen.watt.note_app.ui.screen.sign_in

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import wade.owen.watt.note_app.R
import wade.owen.watt.note_app.ui.theme.NoteAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignInScreen() {
    val viewModel = hiltViewModel<SignInViewModel>()
    val uiState = viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val callbackManager = remember {
        CallbackManager.Factory.create()
    }

    val launcher = rememberLauncherForActivityResult(
        contract = viewModel.loginManager.createLogInActivityResultContract(
            callbackManager = callbackManager,
            loggerID = null
        )
    ) {
        // nothing to do. Handled in FacebookCallback
    }

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
                                }
                            }
                    }
                }
            })
        onDispose {
            viewModel.loginManager.unregisterCallback(callbackManager)
        }
    }

    NoteAppTheme(darkTheme = false) {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = "app_ic"
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
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "ic_fb",
                        modifier = Modifier.clickable {
                            launcher.launch(listOf("public_profile"))
                        },
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_x),
                        contentDescription = "ic_x",
                        modifier = Modifier.clickable {

                        },
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "ic_gg",
                        modifier = Modifier.clickable {

                        },
                    )
                }
            }
        }
    }
}