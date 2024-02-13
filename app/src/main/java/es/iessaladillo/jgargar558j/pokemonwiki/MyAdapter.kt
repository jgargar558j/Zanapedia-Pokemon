package es.iessaladillo.jgargar558j.pokemonwiki

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * MyAdapter es una clase adaptadora que se utiliza para mostrar una lista de Pokémon en una ListView o GridView.
 * Se encarga de inflar el diseño de cada elemento de la lista y asignar los datos correspondientes.
 *
 * @param mContext El contexto de la aplicación.
 * @param listaPokemon La lista de Pokémon que se mostrará.
 */
class MyAdapter(private val mContext: Context, private val listaPokemon: List<Pokemon>) : ArrayAdapter<Pokemon>(mContext, 0, listaPokemon) {

    /**
     * Obtiene una vista que muestra los datos en la posición especificada en la lista.
     *
     * @param position La posición en la lista de datos.
     * @param convertView La vista anteriormente usada para mostrar los datos.
     * @param parent El ViewGroup al que pertenece la vista de retorno.
     * @return Una vista que muestra los datos en la posición especificada en la lista.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflar el diseño de cada elemento de la lista.
        val layout = LayoutInflater.from(mContext).inflate(R.layout.activity_item_pokemon, parent, false)
        // Obtener el Pokémon en la posición actual.
        val pokemones = listaPokemon[position]

        // Asignar el nombre del Pokémon al TextView correspondiente.
        layout.findViewById<TextView>(R.id.TVnombre).text = "${pokemones.name}"
        // Asignar la descripción del Pokémon al TextView correspondiente.
        layout.findViewById<TextView>(R.id.TVdescripcion).text = "${pokemones.description}"
        // Asignar la imagen del Pokémon al ImageView correspondiente.
        layout.findViewById<ImageView>(R.id.IVpokemon).setImageResource(pokemones.imagen)

        return layout
    }
}