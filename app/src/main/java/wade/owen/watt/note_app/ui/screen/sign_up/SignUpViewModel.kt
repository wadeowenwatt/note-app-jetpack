package wade.owen.watt.note_app.ui.screen.sign_up

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private var _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> get() = _uiState


    fun onChangedUsername(username: String) {
        updateUiState(userName = username)
    }

    private fun updateUiState(
        userName: String? = null,
    ) {
        _uiState.update {
            it.copy(
                userName = userName ?: it.userName,
            )
        }
    }

}

data class SignUpUiState(
    val userName: String? = null,
)