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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wade.owen.watt.note_app.R
import wade.owen.watt.note_app.ui.compose.CustomIconButton
import wade.owen.watt.note_app.ui.theme.NoteAppTheme
import wade.owen.watt.note_app.ui.theme.Typography

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteDetailScreen() {
    NoteAppTheme(
        darkTheme = true
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            NoteDetailScreenBody(
                viewingMode = false,
                title = "Hôm nay ăn gì?Hôm nay ăn gì?Hôm nay ăn gì?Hôm nay ăn gì?",
                content = "Hôm nay ăn rất nhiều bún bún bún búnbún bún búnbún bún búnbún bún búnbún bún bún Hôm nay ăn rất nhiều bún bún bún búnHôm nay ăn rất nhiều bún bún bún búnHôm nay ăn rất nhiều bún bún bún búnHôm nay ăn rất nhiều bún bún bún búnHôm nay ăn rất nhiều bún bún bún búnHôm nay ăn rất nhiều bún bún bún búnHôm nay ăn rất nhiều bún bún bún búnHôm nay ăn rất nhiều bún bún bún bún"
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteDetailScreenBody(viewingMode: Boolean, title: String, content: String) {
    var viewingModeState by rememberSaveable {
        mutableStateOf(viewingMode)
    }
    Box(modifier = Modifier.padding(horizontal = 25.dp)) {
        LazyColumn(contentPadding = PaddingValues(bottom = 25.dp)) {
            stickyHeader {
                AppBar(
                    viewingMode = viewingModeState,
                    onSave = {},
                    onEdit = {
                        viewingModeState = false
                    },
                    onPreview = { viewingModeState = true },
                )
            }
            item {
                if (viewingModeState) {
                    ViewingBody(title, content)
                } else {
                    EditBody(title, content)
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBody(title: String, content: String) {
    Column {
        TextField(
            value = "",
            onValueChange = {

            },
            textStyle = Typography.bodyLarge.copy(
                fontSize = 35.sp, lineHeight = 50.sp
            ),
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
            value = content,
            onValueChange = {

            },
            textStyle = Typography.bodyLarge.copy(
                fontSize = 23.sp, lineHeight = 30.sp
            ),

        )
    }
}

@Composable
fun ViewingBody(title: String, content: String) {
    Column(modifier = Modifier.padding(top = 40.dp)) {
        Text(text = title, fontSize = 35.sp, lineHeight = 50.sp)
        Box(modifier = Modifier.height(30.dp))
        Text(text = content, fontSize = 23.sp, lineHeight = 30.sp)
    }
}

@Composable
fun AppBar(onPreview: () -> Unit, onEdit: () -> Unit, onSave: () -> Unit, viewingMode: Boolean) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.weight(fill = true, weight = 1f)) {
            /** Todo **/
            CustomIconButton(
                onClick = { /** Todo **/ },
                icon = Icons.Rounded.KeyboardArrowLeft,
                contentDescription = "search",
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
                painter = painterResource(id = R.drawable.visibility),
                contentDescription = "preview",
            )
            Box(modifier = Modifier.width(21.dp))
            CustomIconButton(
                onClick = onSave,
                painter = painterResource(id = R.drawable.save),
                contentDescription = "info"
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewNoteDetail() {
    NoteDetailScreen()
}