/**
 *  Classes and interface needed to represent the Pokémon hierarchy
 * */
import java.util.ArrayList

/**
 * Printable Interface:
 * Declare the returnInfoString() method, which returns a Pokémon's information as a string
 * */
interface Printable{
    fun returnInfoString(): String
}

/**
 * Trainer class
 * object of Trainer class will contain the Pokémon trainer's information:
 * first name, last name, and trainer number.
 * */
class Trainer(val firstName: String, val lastName: String, val number: Int){
}

/**
 * Pokémon class
 * An abstract class that contains the information of Pokémon:
 * id, type, level, trainer
 * Implements Printable interface (override returnInfoString() function)
 * */
abstract class Pokemon(
    val id:String,
    val type:String,
    var level: Int,
    val trainer: Trainer): Printable{

    override fun returnInfoString(): String{
        return "Id: $id, Type: $type, Level: $level," +
                " Trainer ${trainer.number}: ${trainer.firstName} ${trainer.lastName}"
    }
}

/**
 * CommonPokemon class
 * A class representing common Pokémon, inheriting from the Pokémon class.
 * Additional attribute: equippedItems (list of items the Pokémon is equipped with).
 * */
class CommonPokemon(
    id:String,
    type:String,
    level: Int,
    trainer: Trainer,
    val equippedItems: ArrayList<String> ): Pokemon(id, type, level, trainer){

    override fun returnInfoString(): String{
        return super.returnInfoString() + ", Equipped items: [" + equippedItems.joinToString(", ") + "]"
    }
}

/**
 * SpecialPokemon class
 * A class representing special Pokémon, inheriting from the Pokémon class.
 * Additional attribute: additionalCombatPower (extra combat power of the Pokémon).
 * */
class SpecialPokemon(id:String,
                     type:String,
                     level: Int,
                     trainer: Trainer,
                     val additionalCombatPower: Int ): Pokemon(id, type, level, trainer){

    override fun returnInfoString(): String{
        return super.returnInfoString() + ", Additional Combat Power: " + additionalCombatPower
    }
}

/**
 * LegendaryPokemon class
 * A class representing legendary Pokémon, inheriting from the Pokémon class.
 * Additional attributes: specialAttack (the special attack of the Pokémon),
 * hiddenAbility (the hidden ability of the Pokémon).
 * */
class LegendaryPokemon(id:String,
                       type:String,
                       level: Int,
                       trainer: Trainer,
                       val specialAttack: String,
                       val hiddenAbility: String): Pokemon(id, type, level, trainer){

    override fun returnInfoString(): String{
        return super.returnInfoString() + ", Special Attack: " + specialAttack  + ", Hidden Ability: " + hiddenAbility
    }
}

/**
 * PokemonCenter class
 * A class representing a Pokémon Center where trainers can register their Pokémon.
 * Contains a list of Pokémon and a maximum capacity.
 * */
class  PokemonCenter(){
    private val pokemons: ArrayList<Pokemon> = ArrayList()
    private final val max = 100

    /**
     * getMax method
     * Returns the maximum capacity of the Pokémon Center.
     * */
    fun getMax(): Int{
        return max
    }

    /**
     * getCurrentSize method
     * Returns the current number of Pokémon in the Pokémon Center.
     * */
    fun getCurrentSize():Int{
        return pokemons.size
    }

    /**
     * registerPokemon method
     * Receives an object of type Pokémon and stores it in the structure
     * if there is capacity.
     * Return true or false to indicate if the operation was successful.
     * */
    fun registerPokemon(pokemon: Pokemon): Boolean{
        if (pokemons.size == max) {
            return false
        }
        pokemons.add(pokemon)
        return true
    }

    /**
     * listPokemon method
     * Returns an array of strings, where each element represents
     * basic information about a Pokémon.
     * */
    fun listPokemon(): ArrayList<String>{
        val listInform: ArrayList<String> = ArrayList()
        if (pokemons.size > 0) {
            for (p in pokemons)
                listInform.add(p.returnInfoString())
        }else println("There are no registered Pokémon.\n")
        return listInform
    }

    /**
     * pokemonInfo method
     * Receives a Pokémon ID and returns its detailed information as a string,
     * or null if the Pokémon doesn’t exist.
     * */
    fun  pokemonInfo(id: String): String? {
        for (p in pokemons) {
            if (p.id == id)
                return p.returnInfoString()
        }
        return null
    }

    /**
     * increaseLevel method
     * Receives an ID and a quantity, increasing the Pokémon's level.
     * Returns true or false to indicate success.
     * */
    fun increaseLevel(id:String, quantity: Int):Boolean{
        for (p in pokemons) {
            if (p.id == id) {
                p.level += quantity
                return true
            }
        }
        return false
    }

    /**
     * decreaseLevel method
     *  Receives an ID and a quantity, decreasing the Pokémon’s level if possible
     * (minimum level of 1). Returns true or false to indicate success.
     * */
    fun  decreaseLevel(id:String, quantity: Int):Boolean{
        for (p in pokemons) {
            if (p.id == id) {
                if (p.level - quantity < 1) {
                    println("Current level = ${p.level}, quantity to decrease = $quantity.")
                    println("It isn't posible to decrease the level of Pokemon.")
                    println("The minimum allowed level = 1.")
                    return false
                }
                p.level -= quantity
                return true
            }
        }
        return false
    }

    /**
     * checkLevel method
     * Receives an ID and returns the Pokémon's level or -1 if it doesn’t exist.
     * */
    fun checkLevel(id: String):Int{
        for (p in pokemons) {
            if (p.id == id)
                return p.level
        }
        return -1
    }

    /**
     * assignedId method
     * Receives an ID and
     * returns true if the ID already assigned, false otherwise.
     * */
    fun assignedId(id: String):Boolean{
        for (p in pokemons){
            if (p.id == id)
                return true
        }
        return false
    }

}