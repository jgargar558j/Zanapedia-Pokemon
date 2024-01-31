package es.iessaladillo.jgargar558j.pokemonwiki

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Principal_Activity : AppCompatActivity() {

    private lateinit var lisPokemon:ListView
    private lateinit var names:MutableList<Pokemon>
    private var muestreo:MutableList<Pokemon> = mutableListOf()
    private var busqueda:MutableList<Pokemon> = mutableListOf()
    private lateinit var adapter:MyAdapter
    private lateinit var etBuscador:EditText
    private lateinit var buscador:FloatingActionButton
    private lateinit var pistaBusqueda:String
    private lateinit var ibObjetos:ImageButton
    private lateinit var ibPokemon:ImageButton
    private var isSet:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        initializeVariables()
    }

    @SuppressLint("ResourceType")
    private fun initializeVariables(){
        initializeList()

        ibObjetos = findViewById(R.id.IBObjetos)
        ibPokemon = findViewById(R.id.IBPokemon)
        lisPokemon = findViewById(R.id.lisPokemon)
        etBuscador = findViewById(R.id.ETBuscador)
        buscador = findViewById(R.id.Buscar)
        muestreo.addAll(names)
        adapter = MyAdapter(this,muestreo)
        lisPokemon.adapter = adapter

        lisPokemon.setOnItemClickListener { parent,view,position,id ->
            if (!isSet){
                val intent = Intent(this,PokemonActivity::class.java)
                intent.putExtra("pokemon", names[position])
                var bundle = Bundle()
                var bundleList:ArrayList<Pokemon> = ArrayList(names)
                bundle.putSerializable("fPokemon",bundleList)
                intent.putExtra("bundlelist",bundle)
                if(names[position]==names[0]){
                    intent.putExtra("next",names[position+1])
                }else if(names[position]==names[getString(R.integer.totalNumberOfPokemonInPokedex).toInt()-1]){
                    intent.putExtra("previous",names[position-1])
                }else{
                    intent.putExtra("next",names[position+1])
                    intent.putExtra("previous",names[position-1])
                }
                startActivity(intent)
            }
        }
        buscador.setOnClickListener{
            muestreo.clear()
            busqueda.clear()
            pistaBusqueda = etBuscador.text.toString().lowercase()
            if (!pistaBusqueda.isNullOrBlank()){
                for (i in 0 until (getString(R.integer.totalNumberOfPokemonInPokedex).toInt())){
                    if (names[i].name.lowercase().contains(pistaBusqueda)){
                        busqueda.add(names[i])
                    }
                }
            }
            muestreo.addAll(busqueda)
            if(muestreo.size==0){
                muestreo.addAll(names)
                isSet = false
            }else{
                isSet=true
            }
            adapter.notifyDataSetChanged()
        }
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
    fun initializeList(){
        val pokemon = Pokemon(R.integer.pokemon1Id,getString(R.string.pokemon1Name),getString(R.string.pokemon1_2_3_Description),R.drawable.pokemon1,R.drawable.planta,R.drawable.veneno,R.drawable.pokemon1variocolor,"1")
        val pokemon2 = Pokemon(R.integer.pokemon2Id,getString(R.string.pokemon2Name),getString(R.string.pokemon1_2_3_Description),R.drawable.pokemon2,R.drawable.planta,R.drawable.veneno,R.drawable.pokemon2variocolor,"2")
        val pokemon3 = Pokemon(R.integer.pokemon3Id,getString(R.string.pokemon3Name),getString(R.string.pokemon1_2_3_Description),R.drawable.pokemon3,R.drawable.planta,R.drawable.veneno,R.drawable.pokemon3variocolor,"3")
        val pokemon4 = Pokemon(R.integer.pokemon4Id,getString(R.string.pokemon4Name),getString(R.string.pokemon4_Description),R.drawable.pokemon4,R.drawable.fuego,R.drawable.vacio,R.drawable.pokemon4variocolor,"4")
        val pokemon5 = Pokemon(R.integer.pokemon5Id,getString(R.string.pokemon5Name),getString(R.string.pokemon5_6_Description),R.drawable.pokemon5,R.drawable.fuego,R.drawable.vacio,R.drawable.pokemon5variocolor,"5")
        val pokemon6 = Pokemon(R.integer.pokemon6Id,getString(R.string.pokemon6Name),getString(R.string.pokemon5_6_Description),R.drawable.pokemon6,R.drawable.fuego,R.drawable.volador,R.drawable.pokemon6variocolor,"6")
        val pokemon7 = Pokemon(R.integer.pokemon7Id,getString(R.string.pokemon7Name),getString(R.string.pokemon7_Description),R.drawable.pokemon7,R.drawable.agua,R.drawable.vacio,R.drawable.pokemon7variocolor,"7")
        val pokemon8 = Pokemon(R.integer.pokemon8Id,getString(R.string.pokemon8Name),getString(R.string.pokemon8_Description),R.drawable.pokemon8,R.drawable.agua,R.drawable.vacio,R.drawable.pokemon8variocolor,"8")
        val pokemon9 = Pokemon(R.integer.pokemon9Id,getString(R.string.pokemon9Name),getString(R.string.pokemon9_Description),R.drawable.pokemon9,R.drawable.agua,R.drawable.vacio,R.drawable.pokemon9variocolor,"9")
        val pokemon10 = Pokemon(R.integer.pokemon10Id,getString(R.string.pokemon10Name),getString(R.string.pokemon10_Description),R.drawable.pokemon10,R.drawable.bicho,R.drawable.vacio,R.drawable.pokemon10variocolor,"10")
        val pokemon11 = Pokemon(R.integer.pokemon11Id,getString(R.string.pokemon11Name),getString(R.string.pokemon11_14_Description),R.drawable.pokemon11,R.drawable.bicho,R.drawable.vacio,R.drawable.pokemon11variocolor,"11")
        val pokemon12 = Pokemon(R.integer.pokemon12Id,getString(R.string.pokemon12Name),getString(R.string.pokemon12_Description),R.drawable.pokemon12,R.drawable.bicho,R.drawable.volador,R.drawable.pokemon12variocolor,"12")
        val pokemon13 = Pokemon(R.integer.pokemon13Id,getString(R.string.pokemon13Name),getString(R.string.pokemon13_Description),R.drawable.pokemon13,R.drawable.bicho,R.drawable.veneno,R.drawable.pokemon13variocolor,"13")
        val pokemon14 = Pokemon(R.integer.pokemon14Id,getString(R.string.pokemon14Name),getString(R.string.pokemon11_14_Description),R.drawable.pokemon14,R.drawable.bicho,R.drawable.veneno,R.drawable.pokemon14variocolor,"14")
        val pokemon15 = Pokemon(R.integer.pokemon15Id,getString(R.string.pokemon15Name),getString(R.string.pokemon15_Description),R.drawable.pokemon15,R.drawable.bicho,R.drawable.veneno,R.drawable.pokemon15variocolor,"15")
        val pokemon16 = Pokemon(R.integer.pokemon16Id,getString(R.string.pokemon16Name),getString(R.string.pokemon16_21_Description),R.drawable.pokemon16,R.drawable.normal,R.drawable.volador,R.drawable.pokemon16variocolor,"16")
        val pokemon17 = Pokemon(R.integer.pokemon17Id,getString(R.string.pokemon17Name),getString(R.string.pokemon17_18_Description),R.drawable.pokemon17,R.drawable.normal,R.drawable.volador,R.drawable.pokemon17variocolor,"17")
        val pokemon18 = Pokemon(R.integer.pokemon18Id,getString(R.string.pokemon18Name),getString(R.string.pokemon17_18_Description),R.drawable.pokemon18,R.drawable.normal,R.drawable.volador,R.drawable.pokemon18variocolor,"18")
        val pokemon19 = Pokemon(R.integer.pokemon19Id,getString(R.string.pokemon19Name),getString(R.string.pokemon19_20_25_26_27_28_Description),R.drawable.pokemon19,R.drawable.normal,R.drawable.vacio,R.drawable.pokemon19variocolor,"19")
        val pokemon20 = Pokemon(R.integer.pokemon20Id,getString(R.string.pokemon20Name),getString(R.string.pokemon19_20_25_26_27_28_Description),R.drawable.pokemon20,R.drawable.normal,R.drawable.vacio,R.drawable.pokemon20variocolor,"20")
        val pokemon21 = Pokemon(R.integer.pokemon21Id,getString(R.string.pokemon21Name),getString(R.string.pokemon16_21_Description),R.drawable.pokemon21,R.drawable.normal,R.drawable.volador,R.drawable.pokemon21variocolor,"21")
        val pokemon22 = Pokemon(R.integer.pokemon22Id,getString(R.string.pokemon22Name),getString(R.string.pokemon22_Description),R.drawable.pokemon22,R.drawable.normal,R.drawable.volador,R.drawable.pokemon22variocolor,"22")
        val pokemon23 = Pokemon(R.integer.pokemon23Id,getString(R.string.pokemon23Name),getString(R.string.pokemon23_Description),R.drawable.pokemon23,R.drawable.veneno,R.drawable.vacio,R.drawable.pokemon23variocolor,"23")
        val pokemon24 = Pokemon(R.integer.pokemon24Id,getString(R.string.pokemon24Name),getString(R.string.pokemon24_Description),R.drawable.pokemon24,R.drawable.veneno,R.drawable.vacio,R.drawable.pokemon24variocolor,"24")
        val pokemon25 = Pokemon(R.integer.pokemon25Id,getString(R.string.pokemon25Name),getString(R.string.pokemon19_20_25_26_27_28_Description),R.drawable.pokemon25,R.drawable.electrico,R.drawable.vacio,R.drawable.pokemon25variocolor,"25")
        val pokemon26 = Pokemon(R.integer.pokemon26Id,getString(R.string.pokemon26Name),getString(R.string.pokemon19_20_25_26_27_28_Description),R.drawable.pokemon26,R.drawable.electrico,R.drawable.vacio,R.drawable.pokemon26variocolor,"26")
        val pokemon27 = Pokemon(R.integer.pokemon27Id,getString(R.string.pokemon27Name),getString(R.string.pokemon19_20_25_26_27_28_Description),R.drawable.pokemon27,R.drawable.tierra,R.drawable.vacio,R.drawable.pokemon27variocolor,"27")
        val pokemon28 = Pokemon(R.integer.pokemon28Id,getString(R.string.pokemon28Name),getString(R.string.pokemon19_20_25_26_27_28_Description),R.drawable.pokemon28,R.drawable.tierra,R.drawable.vacio,R.drawable.pokemon28variocolor,"28")
        val pokemon29 = Pokemon(R.integer.pokemon29Id,getString(R.string.pokemon29Name),getString(R.string.pokemon29_30_32_33_Description),R.drawable.pokemon29,R.drawable.veneno,R.drawable.vacio,R.drawable.pokemon29variocolor,"29")
        val pokemon30 = Pokemon(R.integer.pokemon30Id,getString(R.string.pokemon30Name),getString(R.string.pokemon29_30_32_33_Description),R.drawable.pokemon30,R.drawable.veneno,R.drawable.vacio,R.drawable.pokemon30variocolor,"30")
        val pokemon31 = Pokemon(R.integer.pokemon31Id,getString(R.string.pokemon31Name),getString(R.string.pokemon31_34_Description),R.drawable.pokemon31,R.drawable.veneno,R.drawable.tierra,R.drawable.pokemon31variocolor,"31")
        val pokemon32 = Pokemon(R.integer.pokemon32Id,getString(R.string.pokemon32Name),getString(R.string.pokemon29_30_32_33_Description),R.drawable.pokemon32,R.drawable.veneno,R.drawable.vacio,R.drawable.pokemon32variocolor,"32")
        val pokemon33 = Pokemon(R.integer.pokemon33Id,getString(R.string.pokemon33Name),getString(R.string.pokemon29_30_32_33_Description),R.drawable.pokemon33,R.drawable.veneno,R.drawable.vacio,R.drawable.pokemon33variocolor,"33")
        val pokemon34 = Pokemon(R.integer.pokemon34Id,getString(R.string.pokemon34Name),getString(R.string.pokemon31_34_Description),R.drawable.pokemon34,R.drawable.veneno,R.drawable.tierra,R.drawable.pokemon34variocolor,"34")
        val pokemon35 = Pokemon(R.integer.pokemon35Id,getString(R.string.pokemon35Name),getString(R.string.pokemon35_36_Description),R.drawable.pokemon35,R.drawable.normal,R.drawable.vacio,R.drawable.pokemon35variocolor,"35")
        val pokemon36 = Pokemon(R.integer.pokemon36Id,getString(R.string.pokemon36Name),getString(R.string.pokemon35_36_Description),R.drawable.pokemon36,R.drawable.normal,R.drawable.vacio,R.drawable.pokemon36variocolor,"36")
        val pokemon37 = Pokemon(R.integer.pokemon37Id,getString(R.string.pokemon37Name),getString(R.string.pokemon37_38_Description),R.drawable.pokemon37,R.drawable.fuego,R.drawable.vacio,R.drawable.pokemon37variocolor,"37")
        val pokemon38 = Pokemon(R.integer.pokemon38Id,getString(R.string.pokemon38Name),getString(R.string.pokemon37_38_Description),R.drawable.pokemon38,R.drawable.fuego,R.drawable.vacio,R.drawable.pokemon38variocolor,"38")
        val pokemon39 = Pokemon(R.integer.pokemon39Id,getString(R.string.pokemon39Name),getString(R.string.pokemon39_40_Description),R.drawable.pokemon39,R.drawable.normal,R.drawable.vacio,R.drawable.pokemon39variocolor,"39")
        val pokemon40 = Pokemon(R.integer.pokemon40Id,getString(R.string.pokemon40Name),getString(R.string.pokemon39_40_Description),R.drawable.pokemon40,R.drawable.normal,R.drawable.vacio,R.drawable.pokemon40variocolor,"40")
        names = mutableListOf(pokemon,pokemon2,pokemon3,pokemon4,pokemon5,pokemon6,pokemon7,pokemon8,pokemon9,pokemon10,
            pokemon11,pokemon12,pokemon13,pokemon14,pokemon15,pokemon16,pokemon17,pokemon18,pokemon19,pokemon20,
            pokemon21,pokemon22,pokemon23,pokemon24,pokemon25,pokemon26,pokemon27,pokemon28,pokemon29,pokemon30,
            pokemon31,pokemon32,pokemon33,pokemon34,pokemon35,pokemon36,pokemon37,pokemon38,pokemon39,pokemon40)
    }

}