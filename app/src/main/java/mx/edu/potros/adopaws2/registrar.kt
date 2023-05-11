package mx.edu.potros.adopaws2

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class registrar : AppCompatActivity(), View.OnClickListener {
    private var TextEmail: EditText? = null
    private var TextPassword: EditText? = null
    private var btnCrearCuenta: Button? = null
    private var progressDialog: ProgressDialog? = null
    private var firebaseAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        firebaseAuth = FirebaseAuth.getInstance()
        TextEmail = findViewById<View>(R.id.et_email) as EditText
        TextPassword = findViewById<View>(R.id.et_password) as EditText
        btnCrearCuenta = findViewById<View>(R.id.btn_crearCuenta) as Button
        progressDialog = ProgressDialog(this)
        btnCrearCuenta!!.setOnClickListener(this)
    }

    private fun registrarUsuario() {
        val email = TextEmail!!.text.toString().trim { it <= ' ' }
        val password = TextPassword!!.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Se debe ingresar una contraseña", Toast.LENGTH_LONG).show()
            return
        }
        progressDialog!!.setMessage("Registrando usuario...")
        progressDialog!!.show()
        firebaseAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@registrar,
                        "Se ha registrado el usaurio " + TextEmail!!.text,
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@registrar,
                        "No se pudo registrar el usuario",
                        Toast.LENGTH_LONG
                    ).show()
                }
                progressDialog!!.dismiss()
            }
    }

    override fun onClick(v: View) {
        registrarUsuario()
    }
}