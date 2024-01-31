package es.iessaladillo.jgargar558j.pokemonwiki

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PrincipalObjeto_Activity : AppCompatActivity() {

    private lateinit var objects:MutableList<Objeto>
    private var muestreo:MutableList<Objeto> = mutableListOf()
    private var busqueda:MutableList<Objeto> = mutableListOf()
    private lateinit var lisObjeto: ListView
    private lateinit var adapter:MyAdapterObjeto
    private lateinit var ibObjetos:ImageButton
    private lateinit var ibPokemon:ImageButton
    private lateinit var cbEvolucion:CheckBox
    private lateinit var cbCuracion:CheckBox
    private lateinit var fabBuscar:FloatingActionButton
    private lateinit var etBuscadorObjeto:EditText

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

        cbEvolucion = findViewById(R.id.CBEvolucion)
        cbCuracion = findViewById(R.id.CBCuracion)

        adapter = MyAdapterObjeto(this,muestreo)
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
    @SuppressLint("ResourceType")
    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            busqueda.clear()

            when (view.id) {
                R.id.CBCuracion -> {
                    if (checked) {
                        for(i in objects) {
                            if (i.tipo == R.integer.TipoCuracion) {
                                muestreo.add(i)
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }else{
                        for(i in muestreo) {
                            if (getString(i.tipo).toInt() == getString(R.integer.TipoCuracion).toInt()) {
                                muestreo.remove(i)
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
                R.id.CBEvolucion -> {
                    if (checked) {
                        for(i in objects) {
                            if (i.tipo == R.integer.TipoEvolucion) {
                                muestreo.add(i)
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }else{
                        for(i in muestreo) {
                            if (getString(i.tipo).toInt() == getString(R.integer.TipoCuracion).toInt()) {
                                muestreo.remove(i)
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun initializeList(){
        var objeto1 = Objeto(1,"Poción","Cura 20 puntos de salud",R.drawable.pocion,R.integer.TipoCuracion)
        objects = mutableListOf(objeto1)
    }
}