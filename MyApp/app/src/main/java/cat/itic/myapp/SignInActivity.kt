package cat.itic.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        findViewById<Button>(R.id.signInButton)
            .setOnClickListener {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }

        findViewById<TextView>(R.id.alreadyRegisteredText)
            .setOnClickListener {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

        findViewById<Button>(R.id.closeAppButton)
            .setOnClickListener {
                finishAffinity()
            }
    }
}
