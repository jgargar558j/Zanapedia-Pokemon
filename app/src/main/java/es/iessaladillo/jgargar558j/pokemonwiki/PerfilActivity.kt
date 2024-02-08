package es.iessaladillo.jgargar558j.pokemonwiki

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.NumberPicker
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity


class PerfilActivity : AppCompatActivity() {
    private lateinit var tbImagenPerfil:ToggleButton
    private lateinit var npNumero:NumberPicker
    private lateinit var tvNumero:TextView
    private lateinit var ibObjetos: ImageButton
    private lateinit var ibPokemon: ImageButton
    private lateinit var ibVideo: ImageButton
    private lateinit var tvRating:TextView
    private lateinit var sbBarra:SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        initializeVariables()
    }
    fun initializeVariables(){
        sbBarra = findViewById(R.id.SBBarra)
        tvRating = findViewById(R.id.TVRating)
        tbImagenPerfil = findViewById(R.id.TBImagenPerfil)
        npNumero = findViewById(R.id.NPNumeros)
        tvNumero = findViewById(R.id.TVNumero)
        ibVideo = findViewById(R.id.IBVideo)
        ibObjetos = findViewById(R.id.IBObjetos)
        ibPokemon = findViewById(R.id.IBPokemon)
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
                tbImagenPerfil.setBackgroundResource(R.drawable.perfil)
            }else{
                tbImagenPerfil.setBackgroundResource(R.drawable.pokeballobjeto)
            }
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