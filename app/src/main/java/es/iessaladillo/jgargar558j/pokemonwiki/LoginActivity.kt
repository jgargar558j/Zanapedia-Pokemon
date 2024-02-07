package es.iessaladillo.jgargar558j.pokemonwiki

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    private var inicioSesion:Boolean = false
    private var registro:Boolean = false
    private lateinit var tvInicioRegistro:TextView
    private lateinit var etUsuario:EditText
    private lateinit var etContrasena:EditText
    private lateinit var cvCalendario:CalendarView
    private lateinit var btEntrar:Button
    private var usuariosContrasenas = mutableListOf<String>("admin","admin","usuario","123")
    private lateinit var tvDate:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initializeVariables()
    }

    @SuppressLint("ResourceAsColor")
    fun initializeVariables(){
        tvInicioRegistro = findViewById(R.id.TVinicioRegistro)
        etUsuario = findViewById(R.id.ETUsuario)
        etContrasena = findViewById(R.id.ETContrasena)
        cvCalendario = findViewById(R.id.CVCalendario)
        btEntrar = findViewById(R.id.BTEntrar)
        tvDate = findViewById(R.id.TVDate)
        cvCalendario.setOnDateChangeListener{
            cv,year,month,day ->
            tvDate.text = "Fecha: $day/$month/$year"
            tvDate.setTextColor(R.color.black)
        }
    }
    fun botonRegistro(usuario: String,contrasena: String){
        btEntrar.setOnClickListener {
            for (i in 0 until usuariosContrasenas.size){
                if (usuario==usuariosContrasenas[i]&&contrasena==usuariosContrasenas[i+1]){
                    registro = true
                }
            }
            if (!registro){
                usuariosContrasenas.add(usuario)
                usuariosContrasenas.add(contrasena)
                val intent = Intent(this,Principal_Activity::class.java)
                startActivity(intent)
            }
        }
    }
    @SuppressLint("ResourceAsColor")
    fun botonEntrarInicio(usuario:String, contrasena:String){
        btEntrar.setOnClickListener {
            for (i in 0 until usuariosContrasenas.size){
                if (usuario==usuariosContrasenas[i]&&contrasena==usuariosContrasenas[i+1]){
                    inicioSesion = true
                    val intent = Intent(this,Principal_Activity::class.java)
                    startActivity(intent)
                }
            }
            if (!inicioSesion){
                tvInicioRegistro.text = "Usuario o contraseña incorrecto"
                tvInicioRegistro.setTextColor(R.color.purple_500)
            }
        }
    }
    fun onCheckboxClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.RBInicio ->
                    if (checked) {
                        tvInicioRegistro.text = "Inicio Sesión"
                        botonEntrarInicio(etUsuario.text.toString(),etContrasena.text.toString())
                    }
                R.id.RBRegistro ->
                    if (checked) {
                        tvInicioRegistro.text = "Registro"
                        botonRegistro(etUsuario.text.toString(),etContrasena.text.toString())
                    }
            }
        }

    }
}