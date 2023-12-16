package wade.owen.watt.note_app.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wade.owen.watt.note_app.R
import wade.owen.watt.note_app.ui.compose.CustomIconButton
import wade.owen.watt.note_app.ui.screen.home.component.ItemNoteSwipeable
import wade.owen.watt.note_app.ui.theme.Lavender
import wade.owen.watt.note_app.ui.theme.LightGreen
import wade.owen.watt.note_app.ui.theme.MainBg
import wade.owen.watt.note_app.ui.theme.NoteAppTheme
import wade.owen.watt.note_app.ui.theme.PastelYellow
import wade.owen.watt.note_app.ui.theme.SalmonPink
import wade.owen.watt.note_app.ui.theme.SeaGreen
import wade.owen.watt.note_app.ui.theme.Waterspout

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navigateToNoteDetail: (Int) -> Unit) {
    NoteAppTheme(
        darkTheme = true
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /*TODO*/ }, shape = CircleShape,
                    containerColor = MainBg,
                    contentColor = Color.White,
                    modifier = Modifier.size(70.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "add",
                        Modifier.size(40.dp)
                    )
                }
            }
        ) {
            HomeScreenBody(modifier = Modifier, onTapItemNote = navigateToNoteDetail)
        }
    }
}

/// Content Body
@Composable
private fun HomeScreenBody(modifier: Modifier, onTapItemNote: (Int) -> Unit) {
    Box(modifier = Modifier.padding(horizontal = 25.dp)) {
        Column {
            AppBar()
            HasItemBody(onTapItem = onTapItemNote)
        }
// EmptyBody()
    }
}

@Composable
fun HasItemBody(onTapItem: (Int) -> Unit) {
    LazyColumn() {
        items(10) {
            ItemNoteSwipeable(
                id = it,
                title = "Tooi test thu xem sao Tooi test thu xem saoTooi test thu xem saoTooi test thu xem sao",
                onTapItem = { onTapItem.invoke(it) }
            )
        }
    }
}

@Composable
private fun EmptyBody() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.empty_image),
                contentDescription = "empty_image",
                Modifier.size(350.dp)
            )
            Text(text = "Create your first note !", fontWeight = FontWeight.Light)
        }
    }
}

/// Small Component
@Composable
private fun AppBar() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.weight(fill = true, weight = 1f)) {
            Text(
                text = "Notes",
                fontWeight = FontWeight.SemiBold,
                fontSize = 43.sp
            )
        }
        /** Todo **/
        CustomIconButton(
            onClick = { /** Todo **/ },
            icon = Icons.Rounded.Search,
            contentDescription = "search",
        )
        Box(modifier = Modifier.width(21.dp))
        CustomIconButton(
            onClick = { /*TODO*/ },
            icon = Icons.Outlined.Info,
            contentDescription = "info"
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    HomeScreen {}
}