package es.iessaladillo.jgargar558j.pokemonwiki

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.reflect.Modifier

/**
 * Actividad que muestra la información detallada de un Pokémon.
 * Permite al usuario visualizar el nombre, descripción, tipos, número en la Pokédex,
 * imagen, y otras características de un Pokémon específico.
 */
class PokemonActivity : AppCompatActivity() {
    lateinit var tvNombre:TextView
    lateinit var ivPokemon:ImageView
    lateinit var tvDescripcion:TextView
    lateinit var ivTipo1:ImageView
    lateinit var ivTipo2:ImageView
    lateinit var swVarioColor:Switch
    lateinit var tvNumero:TextView
    lateinit var ibAnterior:ImageButton
    lateinit var ibSiguiente:ImageButton
    lateinit var pokemon: Pokemon
    lateinit var fabVolver:FloatingActionButton
    lateinit var ivPokeballLeft:ImageView
    lateinit var ivPokeballRight:ImageView
    lateinit var tvSiguiente:TextView
    lateinit var tvAnterior:TextView
    var next: Pokemon? = null
    var previous: Pokemon? = null
    lateinit var pokedex:ArrayList<Pokemon>

    /**
     * Método llamado cuando se crea la actividad. Se encarga de inicializar la interfaz de usuario
     * y cargar los datos del Pokemon seleccionado.
     */
    @SuppressLint("SetTextI18n", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        // Inicializar las variables de la interfaz de usuario.
        initializeVariables()

        // Obtener el bundle y los datos del Pokemon de la actividad anterior.
        var bundle = Bundle()
        bundle = intent.getBundleExtra("bundlelist")!!
        pokedex = bundle.getSerializable("fPokemon") as ArrayList<Pokemon>
        pokemon = intent.getSerializableExtra("pokemon") as Pokemon

        // Determinar si hay un Pokemon anterior y/o posterior al actual.
        if(intent.getSerializableExtra("previous")==null){
            next = intent.getSerializableExtra("next") as Pokemon
        }else if(intent.getSerializableExtra("next")==null){
            previous = intent.getSerializableExtra("previous") as Pokemon
        }else{
            previous = intent.getSerializableExtra("previous") as Pokemon
            next = intent.getSerializableExtra("next") as Pokemon
        }

        // Mostrar los datos del Pokemon en la interfaz de usuario.
        tvNombre.text = pokemon.name
        tvDescripcion.text = "Descripción: "+pokemon.description
        ivPokemon.setImageResource(pokemon.imagen)
        ivTipo1.setImageResource(pokemon.tipo1)
        ivTipo2.setImageResource(pokemon.tipo2)

        // Configurar el listener para el cambio del switch de variocolor.
        swVarioColor.setOnClickListener {
            var switchState:Boolean = swVarioColor.isChecked
            if (switchState){
                ivPokemon.setImageResource(R.drawable.vacio)
                val drawable = ContextCompat.getDrawable(this, pokemon.imagenVariocolor)
                ivPokemon.background = drawable

                val animator = ObjectAnimator.ofInt(
                    ivPokemon.background,
                    "alpha",
                    0,
                    255
                )
                animator.duration = 3000 // Duración de la animación en milisegundos
                animator.start()

                ObjectAnimator.ofInt(tvNombre,"textColor", ContextCompat.getColor(this, R.color.Verde)).apply {
                    duration = 200
                    start()
                }
            }else{
                ivPokemon.setImageResource(R.drawable.vacio)
                val drawable = ContextCompat.getDrawable(this, pokemon.imagen)
                ivPokemon.background = drawable

                val animator = ObjectAnimator.ofInt(
                    ivPokemon.background,
                    "alpha",
                    0,
                    255
                )
                animator.duration = 3000 // Duración de la animación en milisegundos
                animator.start()
                ObjectAnimator.ofInt(tvNombre,"textColor", ContextCompat.getColor(this, R.color.black)).apply {
                    duration = 200
                    start()
                }
            }
        }

        // Mostrar el número del Pokemon en la Pokédex.
        when(getString(pokemon.id).toInt()){
            in 0..9 -> {tvNumero.text = "#00"+getString(pokemon.id)}
            in 10..99 -> {tvNumero.text = "#0"+getString(pokemon.id)}
            in 100..151 -> {tvNumero.text = "#"+getString(pokemon.id)}
        }

        // Configurar las imágenes y texto de los botones de anterior y siguiente.
        if (((getString(pokemon.id).toInt())==1)){
            ibAnterior.setImageResource(R.drawable.vacio)
            ivPokeballLeft.setImageResource(R.drawable.vacio)
            tvAnterior.text = ""
            ibAnterior.setOnClickListener { }
            if (next != null) {
                ibSiguiente.setImageResource(next!!.imagen)
            }
            ibSiguiente.setOnClickListener {
                next()
            }
        }else if (((getString(pokemon.id).toInt())==(getString(R.integer.totalNumberOfPokemonInPokedex).toInt()))){
            ibSiguiente.setImageResource(R.drawable.vacio)
            ivPokeballRight.setImageResource(R.drawable.vacio)
            tvSiguiente.text = ""
            ibSiguiente.setOnClickListener { }
            if (previous != null) {
                ibAnterior.setImageResource(previous!!.imagen)
            }
            ibAnterior.setOnClickListener {
                previous()
            }
        }else{
            if (next != null) {
                ibSiguiente.setImageResource(next!!.imagen)
            }
            if (previous != null) {
                ibAnterior.setImageResource(previous!!.imagen)
            }
            ibAnterior.setOnClickListener {
                previous()
            }
            ibSiguiente.setOnClickListener {
                next()
            }
        }

        // Configurar el listener del botón de volver.
        fabVolver.setOnClickListener{
            startActivity(Intent(this,Principal_Activity::class.java))
        }
    }

    /**
     * Método invocado al pasar al siguiente Pokemon. Se crea un Intent con los datos del próximo
     * Pokemon y se inicia la actividad PokemonActivity con estos datos.
     */
    @SuppressLint("ResourceType")
    fun next(){
        // Crear un Intent para iniciar la actividad PokemonActivity.
        val intent = Intent(this,PokemonActivity::class.java)

        // Crear un Bundle para pasar la lista de Pokemon.
        var bundle = Bundle()
        var bundleList:ArrayList<Pokemon> = ArrayList(pokedex)
        bundle.putSerializable("fPokemon",bundleList)
        intent.putExtra("bundlelist",bundle)

        // Comprobar si el siguiente Pokemon está al final de la Pokedex.
        if(pokedex[next!!.idString.toInt()-1].idString.toInt()>=getString(R.integer.totalNumberOfPokemonInPokedex).toInt()){
            intent.putExtra("pokemon",next)
            intent.putExtra("previous",pokemon)
        }else{
            // Si no es el último, pasar el Pokemon siguiente y el actual como anterior.
            intent.putExtra("pokemon",next)
            intent.putExtra("previous",pokemon)
            intent.putExtra("next",pokedex[next!!.idString.toInt()])
        }

        // Iniciar la actividad PokemonActivity con el Intent creado.
        startActivity(intent)
    }

    /**
     * Método invocado al retroceder al Pokemon anterior. Se crea un Intent con los datos del Pokemon
     * anterior y se inicia la actividad PokemonActivity con estos datos.
     */
    @SuppressLint("ResourceType")
    fun previous(){
        // Crear un Intent para iniciar la actividad PokemonActivity.
        val intent = Intent(this,PokemonActivity::class.java)

        // Crear un Bundle para pasar la lista de Pokemon.
        var bundle = Bundle()
        var bundleList:ArrayList<Pokemon> = ArrayList(pokedex)
        bundle.putSerializable("fPokemon",bundleList)
        intent.putExtra("bundlelist",bundle)

        // Comprobar si el anterior Pokemon está al inicio de la Pokedex.
        if (pokedex[previous!!.idString.toInt()-1].idString.toInt()<=1){
            intent.putExtra("pokemon",previous)
            intent.putExtra("next",pokemon)
        }else{
            // Si no es el primero, pasar el Pokemon anterior y el actual como siguiente.
            intent.putExtra("pokemon",previous)
            intent.putExtra("next",pokemon)
            intent.putExtra("previous",pokedex[previous!!.idString.toInt()-2])
        }

        // Iniciar la actividad PokemonActivity con el Intent creado.
        startActivity(intent)
    }

    /**
     * Inicializa las variables de la actividad PokemonActivity, asignando las vistas del layout a
     * las correspondientes propiedades de la actividad.
     */
    fun initializeVariables(){
        // Asignar vistas del layout a las propiedades correspondientes.
        tvNombre=findViewById(R.id.TVnombreD)
        ivPokemon=findViewById(R.id.IVpokemonD)
        tvDescripcion=findViewById(R.id.TVdescripcionD)
        ivTipo1=findViewById(R.id.IVtipo1)
        ivTipo2=findViewById(R.id.IVtipo2)
        swVarioColor=findViewById(R.id.SWVarioColor)
        tvNumero=findViewById(R.id.TVnumero)
        ibAnterior=findViewById(R.id.IBAnterior)
        ibSiguiente=findViewById(R.id.IBSiguiente)
        fabVolver=findViewById(R.id.FABVolver)
        ivPokeballLeft=findViewById(R.id.IVPokeballLeft)
        ivPokeballRight=findViewById(R.id.IVPokeballRight)
        tvSiguiente=findViewById(R.id.TVSiguiente)
        tvAnterior=findViewById(R.id.TVAnterior)
    }
}