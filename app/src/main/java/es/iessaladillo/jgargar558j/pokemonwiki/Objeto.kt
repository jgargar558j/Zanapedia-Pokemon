package es.iessaladillo.jgargar558j.pokemonwiki

import java.io.Serializable

/**
 * Objeto es una clase que representa un objeto del mundo Pokémon.
 * Los objetos tienen un identificador único, un nombre, una descripción, una imagen y un tipo.
 *
 * @property int El identificador único del objeto.
 * @property name El nombre del objeto.
 * @property description La descripción del objeto.
 * @property imagen El recurso de imagen del objeto.
 * @property tipo El tipo del objeto.
 */
class Objeto(_int:Int,_name: String, _description: String, _imagen:Int, _tipo:Int) : Serializable{
    var int: Int = _int // El identificador único del objeto.
    var name: String = _name // El nombre del objeto.
    var description: String = _description // La descripción del objeto.
    var imagen: Int = _imagen // El recurso de imagen del objeto.
    var tipo: Int = _tipo // El tipo del objeto.
}