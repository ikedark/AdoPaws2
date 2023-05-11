    package mx.edu.potros.adopaws2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

    class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.login)


        val btn_registrarse: Button = findViewById(R.id.btn_registrate)

        btn_registrarse.setOnClickListener {
            val intent: Intent = Intent(this, registrar::class.java)
            startActivity(intent)
        }

        val btnInicio: Button = findViewById(R.id.btn_login)

        btnInicio.setOnClickListener {
            val intent: Intent = Intent(this, home::class.java)
            startActivity(intent)
        }
    }
}