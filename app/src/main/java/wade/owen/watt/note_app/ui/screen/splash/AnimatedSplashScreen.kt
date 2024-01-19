package wade.owen.watt.note_app.ui.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import wade.owen.watt.note_app.R

@Composable
fun AnimatedSplashScreen(navigateToHome: () -> Unit) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    var alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(3000)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navigateToHome.invoke()
    }
    Splash(alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.app_icon), modifier = Modifier.alpha(alpha), contentDescription = "app-icon")
    }
}