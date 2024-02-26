package wade.owen.watt.note_app.ui.screen.sign_up

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import wade.owen.watt.note_app.ui.theme.NoteAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen() {
    val viewModel: SignUpViewModel = hiltViewModel<SignUpViewModel>()
    val uiState = viewModel.uiState.collectAsState()

    NoteAppTheme(darkTheme = false) {
        Scaffold(Modifier.fillMaxSize()) {
            LazyColumn {
                item { 
                    Text(text = "Type username:")
                    TextField(
                        value = uiState.value.userName ?: "",
                        onValueChange = {
                            viewModel.onChangedUsername(it)
                        },
                    )
                }
            }
        }
    }
}