package wade.owen.watt.note_app.ui.screen.note_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import wade.owen.watt.note_app.data.model.NoteEntity
import wade.owen.watt.note_app.domain.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteRepo: NoteRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private var _uiState = MutableStateFlow(NoteDetailUiState())
    val uiState: StateFlow<NoteDetailUiState> get() = _uiState

    init {
        savedStateHandle.get<String>("id")?.let {
            getNoteDetail(it.toInt())
        }
    }

    private fun getNoteDetail(id: Int?) {
        if (id != null) {
            viewModelScope.launch {
                /// When update note, get note func will call again
                noteRepo.getNote(id).collect {
                    _uiState.value = NoteDetailUiState(
                        id = it.id,
                        title = it.title,
                        tempTitle = it.title,
                        content = it.content,
                        tempContent = it.content,
                        viewingMode = true,
                    )
                }
            }
        }
    }

    fun onChangedTitle(title: String) {
        updateNoteDetail(title = title)
    }

    fun onChangedContent(content: String) {
        updateNoteDetail(content = content)
    }

    fun onChangeViewingMode(status: Boolean) {
        updateNoteDetail(viewingMode = status)
    }

    fun onSaveNote(onBack: () -> Unit) {
        // id null => this is a new note
        if (_uiState.value.id == null) {
            val noteEntity = NoteEntity(
                title = _uiState.value.title ?: "",
                content = _uiState.value.content ?: ""
            )
            viewModelScope.launch {
                noteRepo.insertNote(noteEntity)
            }
            onBack.invoke()
        } else {
            val noteEntity = NoteEntity(
                id = _uiState.value.id ?: -1,
                title = _uiState.value.title ?: "",
                content = _uiState.value.content ?: "",
            )
            viewModelScope.launch {
                noteRepo.updateNote(noteEntity)
            }
        }
    }

    /// Use for update state
    private fun updateNoteDetail(
        title: String? = null,
        content: String? = null,
        viewingMode: Boolean = false,
    ) {
        _uiState.update {
            it.copy(
                title = title ?: it.title,
                content = content ?: it.content,
                viewingMode = viewingMode,
            )
        }
    }

}

data class NoteDetailUiState(
    var id: Int? = null,
    var title: String? = null,
    var tempTitle: String? = null,
    var content: String? = null,
    var tempContent: String? = null,
    var viewingMode: Boolean = false,
)