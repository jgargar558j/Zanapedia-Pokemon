package es.iessaladillo.jgargar558j.pokemonwiki

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * MyAdapterObjeto es una clase adaptadora que se utiliza para mostrar una lista de objetos en una ListView o GridView.
 * Se encarga de inflar el diseño de cada elemento de la lista y asignar los datos correspondientes.
 *
 * @param mContext El contexto de la aplicación.
 * @param listaObjeto La lista de objetos que se mostrará.
 */
class MyAdapterObjeto(private val mContext: Context, private val listaObjeto: List<Objeto>) : ArrayAdapter<Objeto>(mContext, 0, listaObjeto) {

    /**
     * Obtiene una vista que muestra los datos en la posición especificada en la lista.
     *
     * @param position La posición en la lista de datos.
     * @param convertView La vista anteriormente usada para mostrar los datos.
     * @param parent El ViewGroup al que pertenece la vista de retorno.
     * @return Una vista que muestra los datos en la posición especificada en la lista.
     */
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflar el diseño de cada elemento de la lista.
        val layout = LayoutInflater.from(mContext).inflate(R.layout.activity_item_objeto, parent, false)
        // Obtener el objeto en la posición actual.
        val objetos = listaObjeto[position]

        // Asignar el nombre del objeto al TextView correspondiente.
        layout.findViewById<TextView>(R.id.TVnombreObjeto).text = objetos.name
        // Asignar la descripción del objeto al TextView correspondiente.
        layout.findViewById<TextView>(R.id.TVdescripcionObjeto).text = objetos.description
        // Asignar la imagen del objeto al ImageView correspondiente.
        layout.findViewById<ImageView>(R.id.IVObjetoObjeto).setImageResource(objetos.imagen)

        return layout
    }
}