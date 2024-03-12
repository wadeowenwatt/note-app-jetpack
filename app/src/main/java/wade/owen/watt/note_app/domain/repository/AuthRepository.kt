package wade.owen.watt.note_app.domain.repository

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.facebook.CallbackManager.ActivityResultParameters

interface AuthRepository {
    fun signInWithEmail(
        email: String, password: String,
    )

    fun signInWithGoogle(
        context: Context,
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
    )

    fun signInWithFacebook(
        launcher: ManagedActivityResultLauncher<Collection<String>, ActivityResultParameters>
    )
}