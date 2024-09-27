package es.iessaladillo.jgargar558j.pokemonwiki

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Esta clase representa la actividad principal de la aplicación aquí se encontrarán la lista principal de Pokemon, un buscador y donde se desarrolla el mayor porcentaje de la aplicación.
 */
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
    private lateinit var ibInfo:ImageButton
    private var isSet:Boolean = false
    //private lateinit var ibVideo:ImageButton
    //private lateinit var ibPerfil:ImageButton

    /**
     * Método llamado cuando se crea la actividad. Se encarga de inicializar la interfaz de usuario
     * y cargar los datos de los Pokemon de la lista.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        initializeVariables()
    }

    /**
     * Inicializa las variables de la actividad PrincipalActivity, asignando las vistas del layout a
     * las correspondientes propiedades de la actividad.
     */
    @SuppressLint("ResourceType")
    private fun initializeVariables(){
        initializeList()

        //ibPerfil = findViewById(R.id.IBPerfil)
        //ibVideo = findViewById(R.id.IBVideo)
        ibObjetos = findViewById(R.id.IBObjetos)
        ibPokemon = findViewById(R.id.IBPokemon)
        ibInfo = findViewById(R.id.IBInfo)
        lisPokemon = findViewById(R.id.lisPokemon)
        etBuscador = findViewById(R.id.ETBuscador)
        buscador = findViewById(R.id.Buscar)
        muestreo.addAll(names)
        adapter = MyAdapter(this,muestreo)
        lisPokemon.adapter = adapter

        listaPokemonFuncion()
        buscador()

        botonesDeNavegacion()
    }

    /**
     * Configura el listener de clic para los elementos de la lista de Pokémon.
     */
    @SuppressLint("ResourceType")
    fun listaPokemonFuncion(){
        lisPokemon.setOnItemClickListener { parent,view,position,id ->

            // Verifica si la lista de Pokémon está filtrada o no
            if (!isSet){

                // Si no está filtrada, crea un intent para iniciar la actividad PokemonActivity
                val intent = Intent(this,PokemonActivity::class.java)

                // Agrega el Pokémon seleccionado como extra al intent
                intent.putExtra("pokemon", names[position])

                // Crea un bundle para pasar la lista completa de Pokémon como serializable al intent
                var bundle = Bundle()
                var bundleList:ArrayList<Pokemon> = ArrayList(names)
                bundle.putSerializable("fPokemon",bundleList)
                intent.putExtra("bundlelist",bundle)

                // Verifica si el Pokémon seleccionado es el primero en la lista
                if(names[position]==names[0]){
                    // Si es el primero, agrega el siguiente Pokémon como extra al intent
                    intent.putExtra("next",names[position+1])
                }else if(names[position]==names[getString(R.integer.totalNumberOfPokemonInPokedex).toInt()-1]){
                    // Si es el último, agrega el Pokémon anterior como extra al intent
                    intent.putExtra("previous",names[position-1])
                }else{
                    // Si no es el primero ni el último, agrega tanto el siguiente como el anterior Pokémon como extras al intent
                    intent.putExtra("next",names[position+1])
                    intent.putExtra("previous",names[position-1])
                }
                // Inicia la actividad
                startActivity(intent)

            }else{
                // Si la lista está filtrada, realiza la misma lógica pero con la lista filtrada (muestreo)
                val intent = Intent(this,PokemonActivity::class.java)
                intent.putExtra("pokemon",muestreo[position])
                var bundle=Bundle()
                var bundleList:ArrayList<Pokemon> = ArrayList(names)
                bundle.putSerializable("fPokemon",bundleList)
                intent.putExtra("bundlelist",bundle)
                if(muestreo[position]==names[0]){
                    intent.putExtra("next",muestreo[position+1])
                }else if(muestreo[position]==names[names.size-1]){
                    intent.putExtra("previous",muestreo[position-1])
                }else{
                    intent.putExtra("next",names[(names.indexOf(muestreo[position]))+1])
                    intent.putExtra("previous",names[(names.indexOf(muestreo[position]))-1])
                }
                startActivity(intent)
            }
        }
    }

    /**
     * Configura el listener de clic para el botón de búsqueda.
     * Limpia las listas de muestra y de búsqueda, y realiza una búsqueda en la lista de Pokémon según el texto ingresado en el EditText.
     * Si se encuentra alguna coincidencia, se actualiza la lista de muestra con los resultados de la búsqueda.
     * Si no se encuentra ninguna coincidencia, se restaura la lista de muestra con todos los Pokémon.
     * Finalmente, se notifica al adaptador que los datos han cambiado.
     */

    @SuppressLint("ResourceType")
    fun buscador(){
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
    }

    /**
     * Configura los listeners de clic para los botones de navegación.
     */
    fun botonesDeNavegacion(){
        // Listener para el botón de la vista de objetos
        ibObjetos.setOnClickListener {
            val intent = Intent(this,PrincipalObjeto_Activity::class.java)
            startActivity(intent)
        }
        // Listener para el botón de la vista de Pokémon
        ibPokemon.setOnClickListener {
            val intent = Intent(this,Principal_Activity::class.java)
            startActivity(intent)
        }

        ibInfo.setOnClickListener {
            dialogoCopyright()
        }

        // Listener para el botón de la vista de los extras
        /*
        ibVideo.setOnClickListener {
            val intent = Intent(this,VideoViewActivity::class.java)
            startActivity(intent)
        }*/
        // Listener para el botón de la vista del perfil
        /*ibPerfil.setOnClickListener {
            val intent = Intent(this,PerfilActivity::class.java)
            startActivity(intent)
        }*/
    }

    fun dialogoCopyright(){
        AlertDialog.Builder(this)
            .setTitle("Información sobre Copyright")
            .setMessage("La aplicación Zanapedia Pokémon es un proyecto no comercial y sin fines de lucro. Todo el contenido relacionado con la aplicación, incluidos nombres, imágenes, personajes, y cualquier otro material asociado con Pokémon, es propiedad intelectual de Nintendo, Game Freak y The Pokémon Company.\n" +
                    "\n" +
                    "Zanapedia Pokémon no tiene ningún vínculo oficial con estas compañías y no pretende obtener beneficios económicos a través de su uso. Este proyecto se desarrolla con fines informativos y recreativos, respetando los derechos de sus creadores originales. Si Nintendo o cualquiera de las entidades involucradas lo requiere, estamos dispuestos a eliminar el contenido en cuestión o la aplicación en su totalidad.")
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->  })
            .show()
    }

    /**
     * Inicializa la lista de Pokémon con los datos de los Pokémon que existen en la aplicación.
     */
    @SuppressLint("ResourceType")
    fun initializeList(){
        //Anotaciones:
        //  - Necesario hacer la lista de este modo dado a que si se hace de forma "automática" salta una excepción de exceso de memoria.
        //  - Necesario establecer el _idString (copia del id necesaria para establecer diferentes situaciones a través del bundle con el intent) sin estar en la carpeta resources dado a que si no necesitariamos un método virtual que más tarde dará error.
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
        val pokemon41 = Pokemon(R.integer.pokemon41Id,getString(R.string.pokemon41Name),getString(R.string.pokemon41_42_Description),R.drawable.pokemon41,R.drawable.veneno,R.drawable.volador,R.drawable.pokemon41variocolor,"41")
        val pokemon42 = Pokemon(R.integer.pokemon42Id,getString(R.string.pokemon42Name),getString(R.string.pokemon41_42_Description),R.drawable.pokemon42,R.drawable.veneno,R.drawable.volador,R.drawable.pokemon42variocolor,"42")
        val pokemon43 = Pokemon(R.integer.pokemon43Id,getString(R.string.pokemon43Name),getString(R.string.pokemon43_44_Description),R.drawable.pokemon43,R.drawable.planta,R.drawable.veneno,R.drawable.pokemon43variocolor,"43")
        val pokemon44 = Pokemon(R.integer.pokemon44Id,getString(R.string.pokemon44Name),getString(R.string.pokemon43_44_Description),R.drawable.pokemon44,R.drawable.planta,R.drawable.veneno,R.drawable.pokemon44variocolor,"44")
        val pokemon45 = Pokemon(R.integer.pokemon45Id,getString(R.string.pokemon45Name),getString(R.string.pokemon45_Description),R.drawable.pokemon45,R.drawable.planta,R.drawable.veneno,R.drawable.pokemon45variocolor,"45")
        val pokemon46 = Pokemon(R.integer.pokemon46Id,getString(R.string.pokemon46Name),getString(R.string.pokemon46_47_Description),R.drawable.pokemon46,R.drawable.bicho,R.drawable.planta,R.drawable.pokemon46variocolor,"46")
        val pokemon47 = Pokemon(R.integer.pokemon47Id,getString(R.string.pokemon47Name),getString(R.string.pokemon46_47_Description),R.drawable.pokemon47,R.drawable.bicho,R.drawable.planta,R.drawable.pokemon47variocolor,"47")
        val pokemon48 = Pokemon(R.integer.pokemon48Id,getString(R.string.pokemon48Name),getString(R.string.pokemon48_Description),R.drawable.pokemon48,R.drawable.bicho,R.drawable.veneno,R.drawable.pokemon48variocolor,"48")
        val pokemon49 = Pokemon(R.integer.pokemon49Id,getString(R.string.pokemon49Name),getString(R.string.pokemon49_Description),R.drawable.pokemon49,R.drawable.bicho,R.drawable.veneno,R.drawable.pokemon49variocolor,"49")
        val pokemon50 = Pokemon(R.integer.pokemon50Id,getString(R.string.pokemon50Name),getString(R.string.pokemon50_51_Description),R.drawable.pokemon50,R.drawable.tierra,R.drawable.vacio,R.drawable.pokemon50variocolor,"50")
        val pokemon51 = Pokemon(R.integer.pokemon51Id,getString(R.string.pokemon51Name),getString(R.string.pokemon50_51_Description),R.drawable.pokemon51,R.drawable.tierra,R.drawable.vacio,R.drawable.pokemon51variocolor,"51")

        names = mutableListOf(pokemon,pokemon2,pokemon3,pokemon4,pokemon5,pokemon6,pokemon7,pokemon8,pokemon9,pokemon10,
            pokemon11,pokemon12,pokemon13,pokemon14,pokemon15,pokemon16,pokemon17,pokemon18,pokemon19,pokemon20,
            pokemon21,pokemon22,pokemon23,pokemon24,pokemon25,pokemon26,pokemon27,pokemon28,pokemon29,pokemon30,
            pokemon31,pokemon32,pokemon33,pokemon34,pokemon35,pokemon36,pokemon37,pokemon38,pokemon39,pokemon40,
            pokemon41,pokemon42,pokemon43,pokemon44,pokemon45,pokemon46,pokemon47,pokemon48,pokemon49,pokemon50,
            pokemon51)
    }

}