package wade.owen.watt.note_app.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import wade.owen.watt.note_app.ui.theme.IconButtonBg

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