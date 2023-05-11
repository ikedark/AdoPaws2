package mx.edu.potros.adopaws2

import mx.edu.potros.adopaws.DatePickerFragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog

class generarReporte : AppCompatActivity() {
    lateinit var fechaR : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.generar_reporte)

        fechaR  = findViewById(R.id.fechaP)
        fechaR.setOnClickListener { showDatePickerDialog() }

        val btnRegresar : ImageButton = findViewById(R.id.btn_Regresar2)
        val btnReportar: Button = findViewById(R.id.btnReportar)

        btnReportar.setOnClickListener {
            val builder = AlertDialog.Builder(this@generarReporte)

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

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year:Int){
        fechaR.setText("$day/$month/$year")
    }
}