package es.iessaladillo.jgargar558j.pokemonwiki

import java.io.Serializable

/**
 * La clase Pokemon representa a un Pokémon del mundo Pokémon.
 * Cada Pokémon tiene un identificador único, un nombre, una descripción, una imagen,
 * un tipo principal, un tipo secundario (si lo tiene), una imagen variocolor y un identificador de cadena.
 *
 * @property id El identificador único del Pokémon.
 * @property name El nombre del Pokémon.
 * @property description La descripción del Pokémon.
 * @property imagen El recurso de imagen del Pokémon.
 * @property tipo1 El tipo principal del Pokémon.
 * @property tipo2 El tipo secundario del Pokémon.
 * @property imagenVariocolor El recurso de imagen variocolor del Pokémon.
 * @property idString El identificador de cadena del Pokémon.
 */
class Pokemon(_id: Int, _name: String, _description: String, _imagen:Int, _tipo1:Int, _tipo2:Int, _imagenVariocolor:Int, _idString:String) : Serializable{
    var id: Int = _id // El identificador único del Pokémon.
    var name: String = _name // El nombre del Pokémon.
    var description: String = _description // La descripción del Pokémon.
    var imagen: Int = _imagen // El recurso de imagen del Pokémon.
    var tipo1: Int = _tipo1 // El tipo principal del Pokémon.
    var tipo2: Int = _tipo2 // El tipo secundario del Pokémon.
    var imagenVariocolor: Int = _imagenVariocolor // El recurso de imagen variocolor del Pokémon.
    var idString: String = _idString // El identificador de cadena del Pokémon.
}