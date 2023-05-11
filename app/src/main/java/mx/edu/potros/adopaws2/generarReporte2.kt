package mx.edu.potros.adopaws2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import mx.edu.potros.adopaws2.DatePickerFragment

class generarReporte2 : AppCompatActivity() {
    lateinit var fechaR : EditText
    private lateinit var et_description: EditText
    private lateinit var et_lugar: EditText
    private lateinit var et_telefono: EditText
    private lateinit var petImage: ImageView
    private val File = 1
    private lateinit var btn_guardar : Button
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generar_reporte2)
        fechaR = findViewById(R.id.fechaP)
        fechaR.setOnClickListener { showDatePickerDialog() }

        et_description = findViewById(R.id.descP)
        et_lugar = findViewById(R.id.ubicacionP)
        et_telefono = findViewById(R.id.celP)
        btn_guardar = findViewById(R.id.btnReportar)
        petImage = findViewById(R.id.fotoP)

        dbRef = FirebaseDatabase.getInstance().getReference("Petfind")

        val btnRegresar: ImageButton = findViewById(R.id.btn_Regresar2)

        btn_guardar.setOnClickListener {
            saveInfoPet()
            val builder = AlertDialog.Builder(this@generarReporte2)

            val view = layoutInflater.inflate(R.layout.dialog_reporte_creado, null)

            builder.setView(view)

            val dialog = builder.create()

            dialog.show()

            val btnAceptar: Button? = dialog.findViewById(R.id.btnAceptar)

            if (btnAceptar != null) {
                btnAceptar.setOnClickListener {
                    val intent: Intent = Intent(this, Encontrar_Mascota::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                }
            }
        }

        btnRegresar.setOnClickListener {
            val intent: Intent = Intent(this, Encontrar_Mascota::class.java)
            startActivity(intent)
        }
    }

    private fun saveInfoPet() {
        val petDesc = et_description.text.toString()
        val petLocate = et_lugar.text.toString()
        val cellphone = et_telefono.text.toString()
        val dateLost = fechaR.text.toString()
        //petImage.setOnClickListener {
        //  fileUpload()
        //}


        if (petDesc.isEmpty()){
            et_description.error = "Debe de ingresar las señas particulares de la mascota"
        }
        if (petLocate.isEmpty()){
            et_lugar.error = "debe ingresar la dirección donde se perdió a la mascota"
        }
        if (cellphone.isEmpty()){
            et_telefono.error = "Debe de ingresar su telefono de contacto"
        }
        if (dateLost.isEmpty()){
            fechaR.error = "Debe de ingresar la fecha en que perdió a la mascota"
        }

        val petId = dbRef.push().key!!

        val mascota = Mascota_Encontrada(petId, petDesc, null,petLocate, dateLost, cellphone)

        dbRef.child(petId).setValue(mascota)
            .addOnCompleteListener{
                Toast.makeText(this, "Información guardada satisfactoriamente", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        fechaR.setText("$day/$month/$year")
    }
}