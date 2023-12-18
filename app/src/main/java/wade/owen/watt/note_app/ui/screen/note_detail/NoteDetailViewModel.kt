package wade.owen.watt.note_app.ui.screen.note_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import wade.owen.watt.note_app.data.local.dao.NoteDao
import wade.owen.watt.note_app.data.model.NoteEntity
import javax.inject.Inject
@HiltViewModel
class NoteDetailViewModel @Inject constructor(private val noteDao: NoteDao): ViewModel() {
    private var _uiState = MutableStateFlow(NoteDetailUiState())
    val uiState: StateFlow<NoteDetailUiState> get() = _uiState

    init {

    }

    fun onChangedTitle(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    fun onChangedContent(content: String) {
        _uiState.update {
            it.copy(content = content)
        }
    }

    fun onSaveNote() {
        val noteEntity = NoteEntity(
            title = _uiState.value.title!!,
            content = _uiState.value.content!!
        )
        viewModelScope.launch {
            noteDao.insert(noteEntity)
        }
    }

}

data class NoteDetailUiState(
    var title: String? = null,
    var content: String? = null
)