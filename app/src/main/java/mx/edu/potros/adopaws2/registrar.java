package mx.edu.potros.adopaws2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class registrar extends AppCompatActivity implements View.OnClickListener{

    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnCrearCuenta;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        firebaseAuth = FirebaseAuth.getInstance();

        TextEmail = (EditText) findViewById(R.id.et_email);
        TextPassword = (EditText) findViewById(R.id.et_password);

        btnCrearCuenta = (Button) findViewById(R.id.btn_crearCuenta);

        progressDialog = new ProgressDialog(this);

        btnCrearCuenta.setOnClickListener(this);
    }

    private void registrarUsuario(){
        String email = TextEmail.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {

    }
}