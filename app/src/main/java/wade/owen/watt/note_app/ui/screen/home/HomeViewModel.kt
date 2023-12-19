package wade.owen.watt.note_app.ui.screen.home

import android.os.Handler
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import wade.owen.watt.note_app.data.local.dao.NoteDao
import wade.owen.watt.note_app.data.model.NoteEntity
import wade.owen.watt.note_app.domain.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepo: NoteRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState
    init {
        getAllNote()
    }

    private fun getAllNote() {
        /// When note database change getAllNotes auto re-invoke
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)
            noteRepo.getAllNotes().collect {
                _uiState.value = HomeUiState(listNote = it, isLoading = false)
            }
        }
    }

    fun deleteNote(index: Int) {
        val note: NoteEntity? = _uiState.value.listNote?.get(index)
        if (note != null) {
            viewModelScope.launch {
                noteRepo.deleteNote(note)
            }
        }
    }
}

data class HomeUiState(
    var isLoading: Boolean = true,
    var listNote: List<NoteEntity>? = null,
)