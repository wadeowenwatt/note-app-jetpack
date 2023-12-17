package wade.owen.watt.note_app.ui.screen.home

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
        viewModelScope.launch {
            noteRepo.getAllNotes().collect {
                _uiState.value = HomeUiState(listNote = it)
            }
        }
    }
}

data class HomeUiState(
    var listNote: List<NoteEntity>? = null,
)