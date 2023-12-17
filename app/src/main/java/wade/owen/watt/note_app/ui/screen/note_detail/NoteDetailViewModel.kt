package wade.owen.watt.note_app.ui.screen.note_detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import wade.owen.watt.note_app.data.local.dao.NoteDao
import javax.inject.Inject
@HiltViewModel
class NoteDetailViewModel @Inject constructor(private val noteDao: NoteDao): ViewModel() {
    private var _uiState = MutableStateFlow(NoteDetailUiState())
    val uiState: StateFlow<NoteDetailUiState> get() = _uiState

    init {

    }

}

data class NoteDetailUiState(
    var title: String? = null,
    var content: String? = null
)