package wade.owen.watt.note_app.ui.screen.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private var _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> get() = _uiState

    fun updateUser(currentUser: Any?) {
        updateUiState(currentUser)
    }

    private fun updateUiState(
        currentUser: Any? = null,
    ) {
        _uiState.update {
            it.copy(
                currentUser = currentUser ?: it.currentUser
            )
        }
    }
}

data class MainUiState(
    var currentUser: Any? = null,
)