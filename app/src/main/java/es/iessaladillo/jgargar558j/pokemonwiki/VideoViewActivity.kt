package es.iessaladillo.jgargar558j.pokemonwiki

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.VideoView

class VideoViewActivity : AppCompatActivity() {
    lateinit var vvTrailer:VideoView
    lateinit var uri:Uri
    private lateinit var ibObjetos: ImageButton
    private lateinit var ibPokemon: ImageButton
    private lateinit var ibVideo: ImageButton
    private lateinit var wvPagina:WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view)
        initializeVariables()
    }
    fun initializeVariables(){
        ibObjetos = findViewById(R.id.IBObjetos)
        ibPokemon = findViewById(R.id.IBPokemon)
        ibVideo = findViewById(R.id.IBVideo)
        vvTrailer = findViewById(R.id.VVTrailer)
        wvPagina = findViewById(R.id.WVPagina)

        uri = Uri.parse("https://wixmp-ed30a86b8c4ca887773594c2.wixmp.com/v/mp4/6934273a-9e15-4547-ad85-38e162633665/ddqom4a-7e8d1548-581d-49f0-b3f3-50ccfd3c9187.720p.b22e668fd7b945bf9cf5f5ba83504144.mp4")
        vvTrailer.setMediaController((MediaController(this)))
        vvTrailer.setVideoURI(uri)
        vvTrailer.requestFocus()
        vvTrailer.start()

        wvPagina.loadUrl("https://www.mundodeportivo.com/alfabeta/pokemon/esta-evolucion-de-eevee-lo-transforma-en-un-pokemon-de-tipo-bicho")

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