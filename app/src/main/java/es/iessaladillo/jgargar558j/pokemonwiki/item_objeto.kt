package es.iessaladillo.jgargar558j.pokemonwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class item_objeto : AppCompatActivity() {
    lateinit var tvNombre:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_objeto)

        initializeVariables()
    }
    private fun initializeVariables(){
        tvNombre = findViewById(R.id.TVnombreObjeto)
    }
}