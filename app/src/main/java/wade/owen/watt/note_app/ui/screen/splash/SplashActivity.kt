package wade.owen.watt.note_app.ui.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import wade.owen.watt.note_app.MainActivity
import wade.owen.watt.note_app.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        setTheme(R.style.Theme_Splash)
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState, persistentState)
        splashScreen.setKeepOnScreenCondition { true }

        lifecycleScope.launch {
            delay(4000)
            startMainActivity()
            finish()
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
    }
}