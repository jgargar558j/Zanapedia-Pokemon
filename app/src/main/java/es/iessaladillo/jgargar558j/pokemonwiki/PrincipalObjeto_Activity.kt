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
    private lateinit var cbPokeballs:CheckBox

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
        cbPokeballs = findViewById(R.id.CBPokeballs)

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

        cbEvolucion.setOnClickListener{
            onCheckboxClicked(cbEvolucion)
        }
        cbPokeballs.setOnClickListener{
            onCheckboxClicked(cbPokeballs)
        }
        cbCuracion.setOnClickListener{
            onCheckboxClicked(cbCuracion)
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
                        for(i in 0 until muestreo.size) {
                            if (getString(muestreo[i].tipo).toInt() != getString(R.integer.TipoCuracion).toInt()) {
                                busqueda.add(muestreo[i])
                            }
                        }
                        muestreo.clear()
                        muestreo.addAll(busqueda)
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
                        for(i in 0 until muestreo.size) {
                            if (getString(muestreo[i].tipo).toInt() != getString(R.integer.TipoEvolucion).toInt()) {
                                busqueda.add(muestreo[i])
                            }
                        }
                        muestreo.clear()
                        muestreo.addAll(busqueda)
                        adapter.notifyDataSetChanged()
                    }
                }
                R.id.CBPokeballs -> {
                    if (checked) {
                        for(i in objects) {
                            if (i.tipo == R.integer.TipoPokeball) {
                                muestreo.add(i)
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }else{
                        for(i in 0 until muestreo.size) {
                            if (getString(muestreo[i].tipo).toInt() != getString(R.integer.TipoPokeball).toInt()) {
                                busqueda.add(muestreo[i])
                            }
                        }
                        muestreo.clear()
                        muestreo.addAll(busqueda)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun initializeList(){
        var objeto = Objeto(R.integer.objeto1Id,getString(R.string.PokeBall),getString(R.string.PokeBallDescription),R.drawable.pokeballobjeto,R.integer.TipoPokeball)
        var objeto2 = Objeto(R.integer.objeto2Id,getString(R.string.Superball),getString(R.string.SuperballDescription),R.drawable.superball,R.integer.TipoPokeball)
        var objeto3 = Objeto(R.integer.objeto3Id,getString(R.string.Ultraball),getString(R.string.UltraballDescription),R.drawable.ultraball,R.integer.TipoPokeball)
        var objeto4 = Objeto(R.integer.objeto4Id,getString(R.string.MasterBall),getString(R.string.MasterBallDescription),R.drawable.masterball,R.integer.TipoPokeball)
        var objeto5 = Objeto(R.integer.objeto5Id,getString(R.string.HonorBall),getString(R.string.HonorBallDescription),R.drawable.honorball,R.integer.TipoPokeball)
        var objeto6 = Objeto(R.integer.objeto6Id,getString(R.string.AcopioBall),getString(R.string.AcopioBallDescription),R.drawable.acopioball,R.integer.TipoPokeball)
        var objeto7 = Objeto(R.integer.objeto7Id,getString(R.string.LujoBall),getString(R.string.LujoBallDescription),R.drawable.lujoball,R.integer.TipoPokeball)
        var objeto8 = Objeto(R.integer.objeto8Id,getString(R.string.MallaBall),getString(R.string.MallaBallDescription),R.drawable.mallaball,R.integer.TipoPokeball)
        var objeto9 = Objeto(R.integer.objeto9Id,getString(R.string.NidoBall),getString(R.string.NidoBallDescription),R.drawable.nidoball,R.integer.TipoPokeball)
        var objeto10 = Objeto(R.integer.objeto10Id,getString(R.string.TurnoBall),getString(R.string.TurnoBallDescription),R.drawable.turnoball,R.integer.TipoPokeball)
        var objeto11 = Objeto(R.integer.objeto11Id,getString(R.string.SafariBall),getString(R.string.SafariBallDescription),R.drawable.safariball,R.integer.TipoPokeball)
        var objeto12 = Objeto(R.integer.objeto12Id,getString(R.string.PiedraAgua),getString(R.string.PiedraAguaDescription),R.drawable.piedraagua,R.integer.TipoEvolucion)
        var objeto13 = Objeto(R.integer.objeto13Id,getString(R.string.PiedraFuego),getString(R.string.PiedraFuegoDescription),R.drawable.piedrafuego,R.integer.TipoEvolucion)
        var objeto14 = Objeto(R.integer.objeto14Id,getString(R.string.PiedraHoja),getString(R.string.PiedraHojaDescription),R.drawable.piedrahoja,R.integer.TipoEvolucion)
        var objeto15 = Objeto(R.integer.objeto15Id,getString(R.string.PiedraLunar),getString(R.string.PiedraLunarDescription),R.drawable.piedralunar,R.integer.TipoEvolucion)
        var objeto16 = Objeto(R.integer.objeto16Id,getString(R.string.Piedratrueno),getString(R.string.PiedratruenoDescription),R.drawable.piedratrueno,R.integer.TipoEvolucion)
        var objeto17 = Objeto(R.integer.objeto17Id,getString(R.string.Pocion),getString(R.string.PocionDescription),R.drawable.pocion,R.integer.TipoCuracion)
        var objeto18 = Objeto(R.integer.objeto18Id,getString(R.string.Superpocion),getString(R.string.SuperpocionDescription),R.drawable.superpocion,R.integer.TipoCuracion)
        var objeto19 = Objeto(R.integer.objeto19Id,getString(R.string.MaximaPocion),getString(R.string.MaximaPocionDescription),R.drawable.maximapocion,R.integer.TipoCuracion)
        var objeto20 = Objeto(R.integer.objeto20Id,getString(R.string.CuraTotal),getString(R.string.CuraTotalDescription),R.drawable.curatotal,R.integer.TipoCuracion)
        var objeto21 = Objeto(R.integer.objeto21Id,getString(R.string.AguaFresca),getString(R.string.AguaFrescaDescription),R.drawable.aguafresca,R.integer.TipoCuracion)
        var objeto22 = Objeto(R.integer.objeto22Id,getString(R.string.Antidoto),getString(R.string.AntidotoDescription),R.drawable.antidoto,R.integer.TipoCuracion)
        var objeto23 = Objeto(R.integer.objeto23Id,getString(R.string.Antihielo),getString(R.string.AntihieloDescription),R.drawable.antihielo,R.integer.TipoCuracion)
        var objeto24 = Objeto(R.integer.objeto24Id,getString(R.string.Antiparaliz),getString(R.string.AntiparalizDescription),R.drawable.antiparaliz,R.integer.TipoCuracion)
        var objeto25 = Objeto(R.integer.objeto25Id,getString(R.string.Antiquemar),getString(R.string.AntiquemarDescription),R.drawable.antiquemar,R.integer.TipoCuracion)
        var objeto26 = Objeto(R.integer.objeto26Id,getString(R.string.Despertar),getString(R.string.DespertarDescription),R.drawable.despertar,R.integer.TipoCuracion)
        var objeto27 = Objeto(R.integer.objeto27Id,getString(R.string.Revivir),getString(R.string.RevivirDescription),R.drawable.revivir,R.integer.TipoCuracion)
        objects = mutableListOf(objeto,objeto2,objeto3,objeto4,objeto5,objeto6,objeto7,objeto8,objeto9,
            objeto10,objeto11,objeto12,objeto13,objeto14,objeto15,objeto16,objeto17,objeto18,objeto19,
            objeto20,objeto21,objeto22,objeto23,objeto24,objeto25,objeto26,objeto27)
    }
}