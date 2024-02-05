package es.iessaladillo.jgargar558j.pokemonwiki

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapterObjeto(private val mContext: Context, private val listaObjeto: List<Objeto>) : ArrayAdapter<Objeto>(mContext, 0, listaObjeto) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.activity_item_objeto, parent, false)
        val objetos = listaObjeto[position]

        layout.findViewById<TextView>(R.id.TVnombreObjeto).text = objetos.name
        layout.findViewById<TextView>(R.id.TVdescripcionObjeto).text = objetos.description
        layout.findViewById<ImageView>(R.id.IVObjetoObjeto).setImageResource(objetos.imagen)

        return layout
    }
}