package es.iessaladillo.jgargar558j.pokemonwiki

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(private val mContext: Context, private val listaPokemon: List<Pokemon>) : ArrayAdapter<Pokemon>(mContext, 0, listaPokemon) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.activity_item_pokemon, parent, false)
        val pokemones = listaPokemon[position]

        layout.findViewById<TextView>(R.id.TVnombre).text = "${pokemones.name}"
        layout.findViewById<TextView>(R.id.TVdescripcion).text = "${pokemones.description}"
        layout.findViewById<ImageView>(R.id.IVpokemon).setImageResource(pokemones.imagen)

        return layout
    }
}