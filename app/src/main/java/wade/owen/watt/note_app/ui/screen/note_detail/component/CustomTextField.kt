package wade.owen.watt.note_app.ui.screen.note_detail.component

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun CustomTextField(modifier: Modifier = Modifier, textStyle: TextStyle) {
    TextField(value = "", onValueChange = {})
}