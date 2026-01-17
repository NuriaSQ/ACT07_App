package cat.itic.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.loginButton)
            .setOnClickListener {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }

        findViewById<TextView>(R.id.newUserText)
            .setOnClickListener {
                startActivity(Intent(this, SignInActivity::class.java))
            }

        findViewById<Button>(R.id.closeLoginAppButton)
            .setOnClickListener {
                finishAffinity()
            }
    }
}
