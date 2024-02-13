package es.iessaladillo.jgargar558j.pokemonwiki

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
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


class PerfilActivity : AppCompatActivity() {
    private lateinit var tbImagenPerfil:ToggleButton
    private lateinit var npNumero:NumberPicker
    private lateinit var tvNumero:TextView
    private lateinit var ibObjetos: ImageButton
    private lateinit var ibPokemon: ImageButton
    private lateinit var ibVideo: ImageButton
    private lateinit var tvRating:TextView
    private lateinit var sbBarra:SeekBar
    private lateinit var actvPokemonFavorito:AutoCompleteTextView
    private lateinit var pokemons: Array<String?>
    private lateinit var tipos1: Array<String?>
    private lateinit var mactvTiposFavoritos:MultiAutoCompleteTextView
    private lateinit var arrayAdapter:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        initializeVariables()
    }
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
        npNumero.setOnValueChangedListener { picker, oldVal, newVal ->
            val text = "Changed from $oldVal to $newVal"
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            tvNumero.text = newVal.toString()
        }
        sbBarra.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var progressChangedValue = 0
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                progressChangedValue = progress
                tvRating.text = "Rating $progressChangedValue"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }

            override fun onStopTrackingTouch(seekBar: SeekBar?) { }
        })
        tbImagenPerfil.setOnClickListener {
            if(tbImagenPerfil.isChecked){
                tbImagenPerfil.setBackgroundResource(R.drawable.pokeballobjeto)
                ObjectAnimator.ofInt(tvRating,"textColor",ContextCompat.getColor(this, R.color.purple_700)).apply {
                    duration = 500
                    start()
                }
            }else{
                tbImagenPerfil.setBackgroundResource(R.drawable.perfil)
                ObjectAnimator.ofInt(tvRating,"textColor",ContextCompat.getColor(this, R.color.black)).apply {
                    duration = 500
                    start()
                }
            }
        }
        ArrayAdapter(this, android.R.layout.simple_list_item_1, pokemons).also { adapter ->
            actvPokemonFavorito.setAdapter(adapter)
        }

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