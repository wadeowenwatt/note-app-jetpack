package wade.owen.watt.note_app.ui.screen.note_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import wade.owen.watt.note_app.R
import wade.owen.watt.note_app.ui.compose.CustomIconButton
import wade.owen.watt.note_app.ui.theme.NoteAppTheme
import wade.owen.watt.note_app.ui.theme.Typography

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteDetailScreen(navToBack: () -> Unit) {
    val viewModel: NoteDetailViewModel = hiltViewModel<NoteDetailViewModel>()
    val uiState = viewModel.uiState.collectAsState()

    NoteAppTheme(
        darkTheme = true
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            NoteDetailScreenBody(
                state = uiState.value,
                viewModel = viewModel,
                onBack = navToBack,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteDetailScreenBody(
    state: NoteDetailUiState,
    viewModel: NoteDetailViewModel,
    onBack: () -> Unit,
) {
    Box(modifier = Modifier.padding(horizontal = 25.dp)) {
        LazyColumn(contentPadding = PaddingValues(bottom = 25.dp)) {
            stickyHeader {
                AppBar(
                    viewingMode = state.viewingMode,
                    state = state,
                    onBack = { onBack() },
                    onSave = {
                        viewModel.onSaveNote(onBack = onBack)
                    },
                    onEdit = {
                        viewModel.onChangeViewingMode(false)
                    },
                    onPreview = { viewModel.onChangeViewingMode(true) },
                )
            }
            item {
                if (state.viewingMode) {
                    ViewingBody(state)
                } else {
                    EditBody(state, viewModel)
                }
            }

        }
    }
}

@Composable
fun EditBody(state: NoteDetailUiState, viewModel: NoteDetailViewModel) {
    Column {
        TextField(
            value = state.title ?: "",
            onValueChange = {
                viewModel.onChangedTitle(it)
            },
            textStyle = Typography.bodyLarge.copy(
                fontSize = 35.sp, lineHeight = 50.sp
            ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.title_place_holder),
                    style = Typography.bodyLarge.copy(
                        fontSize = 35.sp, lineHeight = 50.sp
                    ),
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
            )
        )
        TextField(
            value = state.content ?: "",
            onValueChange = {
                viewModel.onChangedContent(it)
            },
            textStyle = Typography.bodyLarge.copy(
                fontSize = 23.sp, lineHeight = 30.sp
            ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.content_place_holder),
                    style = Typography.bodyLarge.copy(
                        fontSize = 23.sp, lineHeight = 30.sp
                    ),
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
            )
        )
    }
}

@Composable
fun ViewingBody(state: NoteDetailUiState) {
    Column(modifier = Modifier.padding(top = 30.dp, start = 15.dp)) {
        Text(text = state.title ?: "", fontSize = 35.sp, lineHeight = 50.sp)
        Box(modifier = Modifier.height(30.dp))
        Text(text = state.content ?: "", fontSize = 23.sp, lineHeight = 30.sp)
    }
}

@Composable
fun AppBar(
    onPreview: () -> Unit,
    onEdit: () -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    viewingMode: Boolean,
    state: NoteDetailUiState,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.weight(fill = true, weight = 1f)) {
            CustomIconButton(
                onClick = { onBack() },
                icon = Icons.Rounded.KeyboardArrowLeft,
                contentDescription = "back",
                modifierIcon = Modifier.size(35.dp)
            )
        }
        if (viewingMode) {
            CustomIconButton(
                onClick = onEdit,
                icon = Icons.Outlined.Edit,
                contentDescription = "edit"
            )
        } else {
            CustomIconButton(
                onClick = onPreview,
                enabled = !state.title.isNullOrEmpty() || !state.content.isNullOrEmpty(),
                painter = painterResource(id = R.drawable.visibility),
                contentDescription = "preview",
            )
            Box(modifier = Modifier.width(21.dp))
            CustomIconButton(
                onClick = onSave,
                enabled = !state.title.isNullOrEmpty(),
                painter = painterResource(id = R.drawable.save),
                contentDescription = "info"
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewNoteDetail() {
    NoteDetailScreen {}
}