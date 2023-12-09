package wade.owen.watt.note_app.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import wade.owen.watt.note_app.R
import wade.owen.watt.note_app.ui.theme.IconButtonBg
import wade.owen.watt.note_app.ui.theme.Lavender
import wade.owen.watt.note_app.ui.theme.LightGreen
import wade.owen.watt.note_app.ui.theme.MainBg
import wade.owen.watt.note_app.ui.theme.NoteAppTheme
import wade.owen.watt.note_app.ui.theme.PastelYellow
import wade.owen.watt.note_app.ui.theme.SalmonPink
import wade.owen.watt.note_app.ui.theme.Waterspout

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
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
            HomeScreenBody(modifier = Modifier)
        }
    }
}

@Composable
private fun HomeScreenBody(modifier: Modifier) {
    Box(modifier = Modifier.padding(horizontal = 25.dp)) {
        Column {
            AppBar()
//            Box(modifier = Modifier.height(50.dp))
            HasItemBody()
        }
//                EmptyBody()
    }
}

@Composable
fun HasItemBody() {
    LazyColumn() {
        items(10) {
            ItemNote(id = it, title = "Tooi test thu xem sao")
        }
    }
}

@Composable
fun ItemNote(id: Int, title: String) {
    val color: Color = when (id % 5) {
        0 -> Lavender
        1 -> SalmonPink
        2 -> LightGreen
        3 -> PastelYellow
        else -> Waterspout
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        colors = CardDefaults.cardColors(color)
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 45.dp),
            fontSize = 25.sp,
            color = Color.Black
        )
    }
}

@Composable
private fun AppBar() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
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

@Composable
fun CustomIconButton(onClick: () -> Unit, icon: ImageVector, contentDescription: String) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.size(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = IconButtonBg,
            contentColor = Color.White,
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            Modifier.size(24.dp),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    HomeScreen()
}