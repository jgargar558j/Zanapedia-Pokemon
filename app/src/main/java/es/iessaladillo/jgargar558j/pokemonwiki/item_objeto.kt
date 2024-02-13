package es.iessaladillo.jgargar558j.pokemonwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

/**
 * La actividad ItemObjeto muestra información detallada sobre un objeto específico.
 * Muestra el nombre del objeto en un TextView.
 */
class item_objeto : AppCompatActivity() {
    lateinit var tvNombre:TextView // TextView para mostrar el nombre del objeto.

    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState La instancia anteriormente guardada del estado de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_objeto)

        initializeVariables()
    }

    /**
     * Inicializa las variables de la actividad.
     */
    private fun initializeVariables(){
        tvNombre = findViewById(R.id.TVnombreObjeto)
    }
}