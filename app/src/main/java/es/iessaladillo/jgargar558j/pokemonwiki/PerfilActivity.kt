package es.iessaladillo.jgargar558j.pokemonwiki

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import android.widget.MultiAutoCompleteTextView
import android.widget.MultiAutoCompleteTextView.CommaTokenizer
import android.widget.NumberPicker
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

/**
 * La actividad PerfilActivity permite a los usuarios configurar su perfil y acceder a diferentes funciones.
 * Los usuarios pueden ajustar varios elementos como la imagen de perfil, el número favorito, el rating, etc.
 */
class PerfilActivity : AppCompatActivity() {
    private lateinit var tbImagenPerfil: ToggleButton // Botón para cambiar la imagen de perfil.
    private lateinit var npNumero: NumberPicker // Selector de número favorito.
    private lateinit var tvNumero: TextView // TextView para mostrar el número seleccionado.
    private lateinit var ibObjetos: ImageButton // Botón para acceder a los objetos.
    private lateinit var ibPokemon: ImageButton // Botón para acceder a los Pokémon.
    private lateinit var ibVideo: ImageButton // Botón para acceder a los videos.
    private lateinit var tvRating: TextView // TextView para mostrar el rating.
    private lateinit var sbBarra: SeekBar // Barra para ajustar el rating.
    private lateinit var actvPokemonFavorito: AutoCompleteTextView // AutoCompleteTextView para seleccionar un Pokémon favorito.
    private lateinit var pokemons: Array<String?> // Lista de Pokémon.
    private lateinit var tipos1: Array<String?> // Lista de tipos de Pokémon.
    private lateinit var mactvTiposFavoritos: MultiAutoCompleteTextView // MultiAutoCompleteTextView para seleccionar tipos de Pokémon favoritos.
    private lateinit var arrayAdapter: ArrayAdapter<String> // Adaptador para tipos de Pokémon favoritos.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        initializeVariables()
    }

    /**
     * Inicializa las variables de la actividad.
     */
    fun initializeVariables(){
        pokemons = resources.getStringArray(R.array.pokemonFavorito)
        tipos1 = resources.getStringArray(R.array.tipoFavorito)
        mactvTiposFavoritos = findViewById(R.id.MACTVTiposFavoritos)
        sbBarra = findViewById(R.id.SBBarra)
        tvRating = findViewById(R.id.TVRating)
        tbImagenPerfil = findViewById(R.id.TBImagenPerfil)
        npNumero = findViewById(R.id.NPNumeros)
        tvNumero = findViewById(R.id.TVNumero)
        ibVideo = findViewById(R.id.IBVideo)
        ibObjetos = findViewById(R.id.IBObjetos)
        ibPokemon = findViewById(R.id.IBPokemon)
        actvPokemonFavorito = findViewById(R.id.ACTVPokemonFavorito)

        arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tipos1)
        mactvTiposFavoritos.threshold = 1
        mactvTiposFavoritos.setTokenizer(CommaTokenizer())
        mactvTiposFavoritos.setAdapter(arrayAdapter)

        npNumero.minValue = 0
        npNumero.maxValue = 10
        npNumero.wrapSelectorWheel = true
        numberPickerValues()

        seekBarTouched()
        toggleButtonTouched()

        ArrayAdapter(this, android.R.layout.simple_list_item_1, pokemons).also { adapter ->
            actvPokemonFavorito.setAdapter(adapter)
        }

        ibObjetosAbajo()
    }

    /**
     * Configura el listener de cambio de valor para el NumberPicker que selecciona un número favorito.
     * Cuando se cambia el valor del NumberPicker, se muestra un mensaje Toast con el valor nuevo y se actualiza
     * el TextView que muestra el número seleccionado.
     */
    fun numberPickerValues(){
        npNumero.setOnValueChangedListener { picker, oldVal, newVal ->
            // Cuando se cambia el valor del NumberPicker, se muestra un mensaje Toast con el nuevo valor.
            val text = "Changed from $oldVal to $newVal"
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            // También se actualiza el TextView que muestra el número seleccionado.
            tvNumero.text = newVal.toString()
        }
    }

    /**
     * Configura el listener de cambio de progreso para la SeekBar que ajusta el rating.
     * Cuando se cambia el progreso de la SeekBar, se actualiza el TextView que muestra el rating.
     */
    fun seekBarTouched(){
        sbBarra.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var progressChangedValue = 0
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Cuando se cambia el progreso de la SeekBar, se actualiza el rating.
                progressChangedValue = progress
                tvRating.text = "Rating $progressChangedValue"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }

            override fun onStopTrackingTouch(seekBar: SeekBar?) { }
        })
    }

    /**
     * Configura el listener de clic para el ToggleButton que permite cambiar la imagen de perfil.
     * Cuando se toca el ToggleButton, se cambia la imagen de perfil y el color del TextView según su estado.
     */
    fun toggleButtonTouched(){
        tbImagenPerfil.setOnClickListener {
            if(tbImagenPerfil.isChecked){
                // Si el ToggleButton está activado, se cambia la imagen de perfil y el color del TextView.
                tbImagenPerfil.setBackgroundResource(R.drawable.pokeballobjeto)
                ObjectAnimator.ofInt(tvRating,"textColor",ContextCompat.getColor(this, R.color.purple_700)).apply {
                    duration = 500
                    start()
                }
            }else{
                // Si el ToggleButton está desactivado, se restaura la imagen de perfil y el color del TextView.
                tbImagenPerfil.setBackgroundResource(R.drawable.perfil)
                ObjectAnimator.ofInt(tvRating,"textColor",ContextCompat.getColor(this, R.color.black)).apply {
                    duration = 500
                    start()
                }
            }
        }
    }

    /**
     * Configura los listeners de clic para los ImageButton asociados a las diferentes actividades.
     * Cuando se hace clic en cada ImageButton, se inicia la actividad correspondiente.
     */
    fun ibObjetosAbajo(){
        ibObjetos.setOnClickListener {
            val intent = Intent(this,PrincipalObjeto_Activity::class.java)
            startActivity(intent)
        }
        ibPokemon.setOnClickListener {
            val intent = Intent(this,Principal_Activity::class.java)
            startActivity(intent)
        }
        ibVideo.setOnClickListener {
            val intent = Intent(this,VideoViewActivity::class.java)
            startActivity(intent)
        }
    }
}