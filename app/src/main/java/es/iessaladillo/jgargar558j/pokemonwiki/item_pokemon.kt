package es.iessaladillo.jgargar558j.pokemonwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

/**
 * La actividad ItemPokemon muestra información detallada sobre un Pokémon específico en la lista.
 * Muestra el nombre del Pokémon en un TextView.
 */
class item_pokemon : AppCompatActivity() {
    lateinit var tvNombre:TextView // TextView para mostrar el nombre del Pokémon.


    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState La instancia anteriormente guardada del estado de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_pokemon)

        initializeVariables()
    }

    /**
     * Inicializa las variables de la actividad.
     */
    private fun initializeVariables(){
        tvNombre = findViewById(R.id.TVnombre)
    }
}