package es.iessaladillo.jgargar558j.pokemonwiki

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.reflect.Modifier

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

    @SuppressLint("SetTextI18n", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        initializeVariables()
        var bundle = Bundle()
        bundle = intent.getBundleExtra("bundlelist")!!
        pokedex = bundle.getSerializable("fPokemon") as ArrayList<Pokemon>
        pokemon = intent.getSerializableExtra("pokemon") as Pokemon
        if(intent.getSerializableExtra("previous")==null){
            next = intent.getSerializableExtra("next") as Pokemon
        }else if(intent.getSerializableExtra("next")==null){
            previous = intent.getSerializableExtra("previous") as Pokemon
        }else{
            previous = intent.getSerializableExtra("previous") as Pokemon
            next = intent.getSerializableExtra("next") as Pokemon
        }

        tvNombre.text = pokemon.name
        tvDescripcion.text = "Descripción: "+pokemon.description
        ivPokemon.setImageResource(pokemon.imagen)
        ivTipo1.setImageResource(pokemon.tipo1)
        ivTipo2.setImageResource(pokemon.tipo2)
        swVarioColor.setOnClickListener {
            var switchState:Boolean = swVarioColor.isChecked
            if (switchState){
                ivPokemon.setImageResource(pokemon.imagenVariocolor)
            }else{
                ivPokemon.setImageResource(pokemon.imagen)
            }
        }

        when(getString(pokemon.id).toInt()){
            in 0..9 -> {tvNumero.text = "#00"+getString(pokemon.id)}
            in 10..99 -> {tvNumero.text = "#0"+getString(pokemon.id)}
            in 100..151 -> {tvNumero.text = "#"+getString(pokemon.id)}
        }
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

        fabVolver.setOnClickListener{
            startActivity(Intent(this,Principal_Activity::class.java))
        }
    }

    @SuppressLint("ResourceType")
    fun next(){
        val intent = Intent(this,PokemonActivity::class.java)
        var bundle = Bundle()
        var bundleList:ArrayList<Pokemon> = ArrayList(pokedex)
        bundle.putSerializable("fPokemon",bundleList)
        intent.putExtra("bundlelist",bundle)
        if(pokedex[next!!.idString.toInt()-1].idString.toInt()>=getString(R.integer.totalNumberOfPokemonInPokedex).toInt()){
            intent.putExtra("pokemon",next)
            intent.putExtra("previous",pokemon)
        }else{
            intent.putExtra("pokemon",next)
            intent.putExtra("previous",pokemon)
            intent.putExtra("next",pokedex[next!!.idString.toInt()])
        }
        startActivity(intent)
    }
    @SuppressLint("ResourceType")
    fun previous(){
        val intent = Intent(this,PokemonActivity::class.java)
        var bundle = Bundle()
        var bundleList:ArrayList<Pokemon> = ArrayList(pokedex)
        bundle.putSerializable("fPokemon",bundleList)
        intent.putExtra("bundlelist",bundle)
        if (pokedex[previous!!.idString.toInt()-1].idString.toInt()<=1){
            intent.putExtra("pokemon",previous)
            intent.putExtra("next",pokemon)
        }else{
            intent.putExtra("pokemon",previous)
            intent.putExtra("next",pokemon)
            intent.putExtra("previous",pokedex[previous!!.idString.toInt()-2])
        }
        startActivity(intent)
    }

    fun initializeVariables(){
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