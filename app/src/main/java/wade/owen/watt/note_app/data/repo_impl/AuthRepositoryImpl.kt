package wade.owen.watt.note_app.data.repo_impl

import android.content.Context
import androidx.activity.result.ActivityResult
import android.content.Intent
import android.content.res.Resources
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import wade.owen.watt.note_app.R
import wade.owen.watt.note_app.domain.repository.AuthRepository


@Composable
fun rememberFirebaseAuthLauncher(
    authCompleteCallback: (AuthResult) -> Unit,
    authErrorCallback: (ApiException) -> Unit,
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                scope.launch {
                    val authResult = Firebase.auth.signInWithCredential(credential).await()
                    authCompleteCallback(authResult)
                }
            } catch (e: ApiException) {
                authErrorCallback(e)
            }

        }
    )
}

class AuthRepositoryImpl : AuthRepository {
    override fun signInWithEmail(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun signInWithGoogle(
        context: Context,
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
    ) {
        val launcher = rememberFirebaseAuthLauncher(
            authCompleteCallback = {},
            authErrorCallback = {}
        )
        val requestToken = context.getString(R.string.client_id)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(requestToken).requestEmail().build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        launcher.launch(googleSignInClient.signInIntent)
    }

    override fun signInWithFacebook() {
        TODO("Not yet implemented")
    }
}