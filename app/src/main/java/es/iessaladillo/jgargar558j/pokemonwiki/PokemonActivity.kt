package es.iessaladillo.jgargar558j.pokemonwiki

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView

class PokemonActivity : AppCompatActivity() {
    lateinit var tvNombre:TextView
    lateinit var ivPokemon:ImageView
    lateinit var tvDescripcion:TextView
    lateinit var ivTipo1:ImageView
    lateinit var ivTipo2:ImageView
    lateinit var swVarioColor:Switch
    lateinit var tvNumero:TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        initializeVariables()
        val pokemon = intent.getSerializableExtra("pokemon") as Pokemon
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
    }
    fun initializeVariables(){
        tvNombre=findViewById(R.id.TVnombreD)
        ivPokemon=findViewById(R.id.IVpokemonD)
        tvDescripcion=findViewById(R.id.TVdescripcionD)
        ivTipo1=findViewById(R.id.IVtipo1)
        ivTipo2=findViewById(R.id.IVtipo2)
        swVarioColor=findViewById(R.id.SWVarioColor)
        tvNumero=findViewById(R.id.TVnumero)
    }
}