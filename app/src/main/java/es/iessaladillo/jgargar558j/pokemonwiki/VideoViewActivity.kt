package es.iessaladillo.jgargar558j.pokemonwiki

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.VideoView

/**
 * Actividad para reproducir un video y mostrar una página web.
 */
class VideoViewActivity : AppCompatActivity() {
    lateinit var vvTrailer:VideoView
    lateinit var uri:Uri
    private lateinit var ibObjetos: ImageButton
    private lateinit var ibPokemon: ImageButton
    private lateinit var ibVideo: ImageButton
    private lateinit var wvPagina:WebView

    /**
     * Método llamado cuando se crea la actividad.
     * @param savedInstanceState El estado de la instancia previamente guardada de esta actividad, si la hubiera.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view)
        initializeVariables()
    }

    /**
     * Inicializa las variables de la actividad.
     */
    fun initializeVariables(){
        // Inicialización de ImageButton para la navegación
        ibObjetos = findViewById(R.id.IBObjetos)
        ibPokemon = findViewById(R.id.IBPokemon)
        ibVideo = findViewById(R.id.IBVideo)

        // Inicialización de VideoView para reproducir el tráiler
        vvTrailer = findViewById(R.id.VVTrailer)
        // Inicialización de WebView para cargar la página web
        wvPagina = findViewById(R.id.WVPagina)

        // Definición de la URI del video
        uri = Uri.parse("https://wixmp-ed30a86b8c4ca887773594c2.wixmp.com/v/mp4/6934273a-9e15-4547-ad85-38e162633665/ddqom4a-7e8d1548-581d-49f0-b3f3-50ccfd3c9187.720p.b22e668fd7b945bf9cf5f5ba83504144.mp4")
        // Asignación de un MediaController al VideoView
        vvTrailer.setMediaController((MediaController(this)))
        // Asignación de la URI al VideoView
        vvTrailer.setVideoURI(uri)
        // Establece el foco en el VideoView
        vvTrailer.requestFocus()
        // Inicia la reproducción del video
        vvTrailer.start()

        // Carga la URL en el WebView
        wvPagina.loadUrl("https://www.mundodeportivo.com/alfabeta/pokemon/esta-evolucion-de-eevee-lo-transforma-en-un-pokemon-de-tipo-bicho")

        botonesNavegacion()
    }

    /**
     * Configura los listeners de clic para los botones de navegación.
     */
    fun botonesNavegacion(){
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
        // Listener para el botón de la vista de los extras
        ibVideo.setOnClickListener {
            val intent = Intent(this,VideoViewActivity::class.java)
            startActivity(intent)
        }
    }
}