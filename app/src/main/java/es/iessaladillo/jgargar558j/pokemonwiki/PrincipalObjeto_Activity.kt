package es.iessaladillo.jgargar558j.pokemonwiki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView

class PrincipalObjeto_Activity : AppCompatActivity() {

    private lateinit var objects:MutableList<Objeto>
    private lateinit var lisObjeto: ListView
    private lateinit var adapter:MyAdapterObjeto
    private lateinit var ibObjetos:ImageButton
    private lateinit var ibPokemon:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_objeto)
        initializeVariables()
    }

    private fun initializeVariables(){
        initializeList()
        lisObjeto = findViewById(R.id.lisObjetos)
        ibObjetos = findViewById(R.id.IBObjetos)
        ibPokemon = findViewById(R.id.IBPokemon)

        adapter = MyAdapterObjeto(this,objects)
        lisObjeto.adapter = adapter
        ibObjetos.setOnClickListener {
            val intent = Intent(this,PrincipalObjeto_Activity::class.java)
            startActivity(intent)
        }
        ibPokemon.setOnClickListener {
            val intent = Intent(this,Principal_Activity::class.java)
            startActivity(intent)
        }
    }
    private fun initializeList(){
        var objeto1 = Objeto(1,"Poción","Cura 20 puntos de salud",R.drawable.pocion)
        objects = mutableListOf(objeto1)
    }
}