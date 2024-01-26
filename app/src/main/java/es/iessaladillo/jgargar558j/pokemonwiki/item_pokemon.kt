package es.iessaladillo.jgargar558j.pokemonwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class item_pokemon : AppCompatActivity() {
    lateinit var tvNombre:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_pokemon)

        initializeVariables()
    }
    private fun initializeVariables(){
        tvNombre = findViewById(R.id.TVnombre)
    }
}