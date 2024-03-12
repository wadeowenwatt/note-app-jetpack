package wade.owen.watt.note_app.data.repo_impl

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.facebook.CallbackManager.ActivityResultParameters
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import wade.owen.watt.note_app.R
import wade.owen.watt.note_app.domain.repository.AuthRepository


class AuthRepositoryImpl : AuthRepository {
    override fun signInWithEmail(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun signInWithGoogle(
        context: Context,
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
    ) {
        val requestToken = context.getString(R.string.client_id)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(requestToken).requestEmail().build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        launcher.launch(googleSignInClient.signInIntent)
    }

    override fun signInWithFacebook(
        launcher: ManagedActivityResultLauncher<Collection<String>, ActivityResultParameters>
    ) {
        launcher.launch(listOf("public_profile"))
    }
}