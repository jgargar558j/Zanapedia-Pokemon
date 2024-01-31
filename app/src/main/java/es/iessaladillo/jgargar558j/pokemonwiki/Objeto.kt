package es.iessaladillo.jgargar558j.pokemonwiki

import java.io.Serializable

class Objeto(_int:Int,_name: String, _description: String, _imagen:Int, _tipo:Int) : Serializable{
    var int:Int = _int
    var name:String = _name
    var description:String = _description
    var imagen:Int = _imagen
    var tipo:Int = _tipo
}