package es.iessaladillo.jgargar558j.pokemonwiki

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * LoginActivity es la actividad principal de inicio de sesión y registro.
 * Los usuarios pueden iniciar sesión con sus credenciales existentes o registrarse como nuevos usuarios.
 */
class LoginActivity : AppCompatActivity() {
    private var inicioSesion:Boolean = false // Indica si se ha iniciado sesión con éxito.
    private var registro:Boolean = false // Indica si se ha completado el proceso de registro.
    private lateinit var tvInicioRegistro:TextView // TextViewpara mostrar si está en la pestaña de inicio sesión o en la pestaña registro
    private lateinit var etUsuario:EditText // Campo de texto para el nombre de usuario.
    private lateinit var etContrasena:EditText // Campo de texto para la contraseña.
    private lateinit var cvCalendario:CalendarView // Calentario para seleccionar la fecha de registro.
    private lateinit var btEntrar:Button // Boton para inicio sesión o registrar.
    private var usuariosContrasenas = mutableListOf<String>("admin","admin","usuario","123") // Lista de usuarios y contraseñas registrados de forma base.
    private lateinit var tvDate:TextView // TextView para mostrar la fecha seleccionada en el calendario

    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState La instancia anteriormente guardada del estado de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initializeVariables()
    }

    /**
     * Inicializa las variables de la actividad.
     */
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

    /**
     * Maneja el evento de clic en el botón de registro.
     *
     * @param usuario    El nombre de usuario proporcionado.
     * @param contrasena La contraseña proporcionada.
     */
    fun botonRegistro(){
        btEntrar.setOnClickListener {
            for (i in 0 until usuariosContrasenas.size){
                if (etUsuario.text.toString()==usuariosContrasenas[i]&&etContrasena.text.toString()==usuariosContrasenas[i+1]){
                    registro = true
                }
            }
            if (!registro){
                usuariosContrasenas.add(etUsuario.text.toString())
                usuariosContrasenas.add(etContrasena.text.toString())
                val intent = Intent(this,Principal_Activity::class.java)
                startActivity(intent)
            }
        }
    }

    /**
     * Maneja el evento de clic en el botón de inicio de sesión.
     *
     * @param usuario    El nombre de usuario proporcionado.
     * @param contrasena La contraseña proporcionada.
     */
    @SuppressLint("ResourceAsColor")
    fun botonEntrarInicio(){
        btEntrar.setOnClickListener {
            for (i in 0 until usuariosContrasenas.size-1){
                if (etUsuario.text.toString().equals(usuariosContrasenas[i])&&etContrasena.text.toString().equals(usuariosContrasenas[i+1])){
                    inicioSesion = true
                }
            }
            if (!inicioSesion){
                tvInicioRegistro.text = "Usuario o contraseña incorrecto"
                tvInicioRegistro.setTextColor(R.color.purple_500)
            }else{
                val intent = Intent(this,Principal_Activity::class.java)
                startActivity(intent)
            }
        }
    }

    /**
     * Maneja el evento de clic en los botones de radio para elegir entre inicio de sesión y registro.
     *
     * @param view La vista del botón de radio seleccionado.
     */
    fun onCheckboxClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.RBInicio ->
                    if (checked) {
                        tvInicioRegistro.text = "Inicio Sesión"
                        botonEntrarInicio()
                    }
                R.id.RBRegistro ->
                    if (checked) {
                        tvInicioRegistro.text = "Registro"
                        botonRegistro()
                    }
            }
        }

    }
}