package es.iessaladillo.jgargar558j.pokemonwiki

import java.io.Serializable

class Pokemon(_id: Int, _name: String, _description: String, _imagen:Int, _tipo1:Int, _tipo2:Int, _imagenVariocolor:Int, _idString:String) : Serializable{
    var id:Int = _id
    var name:String = _name
    var description:String = _description
    var imagen:Int = _imagen
    var tipo1:Int = _tipo1
    var tipo2:Int = _tipo2
    var imagenVariocolor:Int = _imagenVariocolor
    var idString:String = _idString
}