package wade.owen.watt.note_app.ui.screen.home.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import wade.owen.watt.note_app.R
import wade.owen.watt.note_app.ui.compose.AppAlertDialog
import wade.owen.watt.note_app.ui.theme.Lavender
import wade.owen.watt.note_app.ui.theme.LightGreen
import wade.owen.watt.note_app.ui.theme.PastelYellow
import wade.owen.watt.note_app.ui.theme.SalmonPink
import wade.owen.watt.note_app.ui.theme.SeaGreen
import wade.owen.watt.note_app.ui.theme.Waterspout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemNoteSwipeable(id: Int, title: String, onTapItem: () -> Unit, onTapDelete: () -> Unit) {
    val deleteVisibility = rememberSaveable {
        mutableStateOf(false)
    }
    val openAlertDialog = rememberSaveable {
        mutableStateOf(false)
    }

    when {
        openAlertDialog.value -> {
            AppAlertDialog(
                textConfirmBtn = stringResource(id = R.string.delete),
                textRejectBtn = stringResource(id = R.string.cancel),
                title = stringResource(id = R.string.delete_note),
                onDismissRequest = {
                    openAlertDialog.value = false
                    deleteVisibility.value = false
                },
                onReject = {
                    openAlertDialog.value = false
                    deleteVisibility.value = false
                },
                onConfirm = {
                    onTapDelete()
                    deleteVisibility.value = false
                },
            )
        }
    }
    val swipeState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                deleteVisibility.value = !deleteVisibility.value
            }
            false
        },
        positionalThreshold = {
            200.dp.toPx()
        }
    )

    SwipeToDismiss(
        state = swipeState,
        directions = setOf(DismissDirection.EndToStart),
        background = {
            BackgroundDismiss(dismissState = swipeState, deleteState = deleteVisibility)
        },
        dismissContent = {
            ItemNote(
                id = id,
                title = title,
                deleteVisibility = deleteVisibility,
                onTapItem = onTapItem,
                onTapDelete = {
                    openAlertDialog.value = true
                },
            )
        },
    )
}

@Composable
fun ItemNote(
    id: Int,
    title: String,
    deleteVisibility: MutableState<Boolean>,
    onTapItem: () -> Unit,
    onTapDelete: () -> Unit
) {
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
            .padding(top = 25.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        Log.e("linhtn1", "Note Item Tap!")
                        onTapItem()
                    },
                )
            },
        colors = CardDefaults.cardColors(color),
    ) {
        Box {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 45.dp),
                fontSize = 25.sp,
                color = Color.Black
            )
            if (deleteVisibility.value) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .matchParentSize()
                        .background(Color.Red),
                ) {
                    IconButton(
                        onClick = {
                            Log.e("linhtn1", "Note Item delete press!")
                            onTapDelete()
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "delete",
                            Modifier.size(50.dp),
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackgroundDismiss(dismissState: DismissState, deleteState: MutableState<Boolean>) {
    Card(
        modifier = Modifier.padding(top = 25.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (deleteState.value) SeaGreen else Color.Red),
        ) {
            IconButton(
                onClick = { Log.e("linhtn1", "Note Item delete press!") },
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Icon(
                    imageVector = if (deleteState.value) {
                        Icons.Rounded.ArrowBack
                    } else {
                        Icons.Rounded.Delete
                    },
                    contentDescription = "icon",
                    Modifier.size(50.dp),
                    tint = Color.White
                )
            }
        }
    }
}