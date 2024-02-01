package wade.owen.watt.note_app.ui.screen.sign_in

import androidx.lifecycle.ViewModel
import com.facebook.login.LoginManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {
    private var _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> get() = _uiState
    val loginManager = LoginManager.getInstance()
    fun onChangedUsername(username: String) {
        updateUiState(username = username)
    }
    fun onChangedPassword(password: String) {
        updateUiState(password = password)
    }

    fun loginWithFacebook() {

    }

    private fun updateUiState(
        username: String? = null,
        password: String? = null,
    ) {
        _uiState.update {
            it.copy(
                username = username ?: it.username,
                password = password ?: it.password,
            )
        }
    }
}

data class SignInUiState(
    var username: String? = null,
    var password: String? = null,
)