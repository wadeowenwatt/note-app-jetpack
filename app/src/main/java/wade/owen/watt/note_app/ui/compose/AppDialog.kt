package wade.owen.watt.note_app.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import wade.owen.watt.note_app.ui.theme.GreyDark
import wade.owen.watt.note_app.ui.theme.GreyText
import wade.owen.watt.note_app.ui.theme.MainBg
import wade.owen.watt.note_app.ui.theme.NoteAppTheme
import wade.owen.watt.note_app.ui.theme.PurpleGrey80
import wade.owen.watt.note_app.ui.theme.SeaGreen
import wade.owen.watt.note_app.ui.theme.Typography

@Composable
fun InfoDialog(onDismissRequest: () -> Unit) {
    NoteAppTheme(
        darkTheme = true
    ) {
        Dialog(
            onDismissRequest = onDismissRequest,
            content = {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(MainBg),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 28.dp, end = 28.dp, top = 38.dp, bottom = 40.dp)
                    ) {
                        GreyTextDialog(text = "Designed by - ")
                        GreyTextDialog(text = "Redesigned by - ")
                        GreyTextDialog(text = "Illustrations - ")
                        GreyTextDialog(text = "Icons - ")
                        GreyTextDialog(text = "Font - ")
                        Box(modifier = Modifier.height(20.dp))
                        GreyTextDialog(
                            text = "Made by",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InfoDialogPreview() {
    InfoDialog {}
}

@Composable
fun AppAlertDialog(
    onDismissRequest: () -> Unit,
    title: String = "",
    description: String = "",
    textConfirmBtn: String = "Save",
    textRejectBtn: String = "Discard",
    onConfirm: () -> Unit,
    onReject: () -> Unit,
) {
    NoteAppTheme(
        darkTheme = true
    ) {
        Dialog(onDismissRequest = onDismissRequest) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(MainBg),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 38.dp, bottom = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "info",
                            tint = GreyDark,
                            modifier = Modifier.align(
                                Alignment.Center
                            ),
                        )
                    }
                    Box(modifier = Modifier.height(20.dp))
                    GreyTextDialog(text = title)
                    if (description.isNotEmpty()) GreyTextDialog(text = description)
                    Box(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(Modifier.weight(fill = true, weight = 0.5f)) {
                            ElevatedButton(
                                onClick = onReject,
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red
                                )
                            ) {
                                Text(
                                    text = textRejectBtn,
                                    style = Typography.bodyLarge.copy(color = Color.White)
                                )
                            }
                        }
                        Box(modifier = Modifier.weight(fill = true, weight = 0.15f))
                        Box(Modifier.weight(fill = true, weight = 0.5f)) {
                            ElevatedButton(
                                onClick = onConfirm,
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = SeaGreen
                                )
                            ) {
                                Text(
                                    text = textConfirmBtn,
                                    style = Typography.bodyLarge.copy(color = Color.White)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreyTextDialog(
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = Typography.bodyLarge.copy(color = GreyText),
        modifier = modifier,
        textAlign = textAlign,
    )
}

@Preview(showBackground = true)
@Composable
fun AppAlertDialogPreview() {
    AppAlertDialog(
        title = "Save changes ?",
        description = "",
        onConfirm = {},
        onReject = {},
        onDismissRequest = {},
    )
}