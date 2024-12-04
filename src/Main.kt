/**
 * Project: Pokémon Center Management
 * Author: Vitaliya Skral
 * Version: 1.0
 * Description:
 *  This program manages a Pokémon Center
 *  where trainers can register and manage their Pokémon
 *  (through a menu, the user can choose from several options to
 *  interact with their Pokémon).
 */
fun main() {

    // Create an instance of PokemonCenter
    val pokemonCenter = PokemonCenter()

//    -----------------------------------------
//    Create Pokémon to test the functionality of the program
//    val trainers = listOf(
//        Trainer("Ash", "Ketchum", 1),
//        Trainer("Misty", "Waterflower", 2),
//        Trainer("Brock", "Stone", 3),
//        Trainer("Gary", "Oak", 4),
//        Trainer("Serena", "Grace", 5)
//    )
//
//    val pokemons = listOf(
//        CommonPokemon("P0001", "fire", 10, trainers[0], arrayListOf("Charcoal", "Berry")),
//        CommonPokemon("P0002", "water", 8, trainers[1], arrayListOf("Mystic Water")),
//        CommonPokemon("P0003", "grass", 12, trainers[2], arrayListOf("Miracle Seed")),
//        SpecialPokemon("P0004", "electric", 15, trainers[0], 20),
//        SpecialPokemon("P0005", "flying", 18, trainers[1], 30),
//        LegendaryPokemon("P0006", "psychic", 25, trainers[2], "Mind Blast", "Telepathy"),
//        LegendaryPokemon("P0007", "dragon", 30, trainers[3], "Dragon Pulse", "Multiscale"),
//        CommonPokemon("P0008", "normal", 5, trainers[4], arrayListOf("Silk Scarf")),
//        SpecialPokemon("P0009", "dark", 20, trainers[0], 25),
//        LegendaryPokemon("P0010", "ghost", 35, trainers[1], "Shadow Force", "Cursed Body")
//    )
//
//    for (pokemon in pokemons) {
//        pokemonCenter.registerPokemon(pokemon)
//    }

    //-----------------------------------------

    //The initial option to start menu
    val option = 0

    //Run menu until the user selects the exit option (7)
    while (option != 7){
        //Display menu options
        menu()
        //Read user input for menu option
        val option = readLine()?.toIntOrNull() ?: 0

        when(option){
            1 -> {
                // Register a new Pokémon if the storage capacity allows it
                if(!(pokemonCenter.getCurrentSize() == pokemonCenter.getMax())) {
                    println("Registering the new Pokémon data...")
                    println("\tPokémon id (with format - PXXXX):")

                    // Read the Pokémon ID, validate its format, and check if the ID is available.
                    var id = readLine()?: ""
                    while (!validFormat(id) || pokemonCenter.assignedId(id)) {
                        if (!validFormat(id)){
                            println("Invalid id format. Please try again...")
                            id = readLine()?: ""
                        }else if (pokemonCenter.assignedId(id)){
                            println("Pokemon id '$id' already exists. Please try again... ")
                            id = readLine()?: ""
                        }
                    }
                    // Read Pokémon type and level
                    println("Pokémon type (fire, water, grass...):")
                    val type = readLine() ?: ""
                    println("Pokémon's level:")
                    val level = readLine()?.toIntOrNull() ?: 0

                    // Read Trainer's data
                    println("Enter Trainer's data:")
                    println("\tfirst name:")
                    val firstName = readLine() ?: ""
                    println("\tlast name:")
                    val lastName = readLine() ?: ""
                    println("\tnumber:")
                    val number = readLine()?.toIntOrNull() ?: 0
                    val trainer = Trainer(firstName, lastName, number)

                    // Read Pokémon category and process each case
                    println("Pokémon category (common, special, legendary):")
                    do{
                        val category = readLine() ?: ""

                        when (category) {
                            //Treat Common Pokémon
                            "common" -> {
                                var count = 1
                                val equippedItems = arrayListOf<String>()

                                println("Enter equipped items (type 'f' to finish): ")
                                do {
                                    print("\titem $count: ")
                                    val item = readLine() ?: ""
                                    if (!item.equals("f")) {
                                        equippedItems.add(item)
                                        count++
                                    }
                                } while (!item.equals("f"))

                                // Create and register CommonPokemon
                                val pokemon = CommonPokemon(id, type, level, trainer, equippedItems)
                                if (pokemonCenter.registerPokemon(pokemon)) {
                                    println("Pokemon:")
                                    println("\t${pokemon.returnInfoString()}")
                                    println("successfully registered!\n")
                                }else {
                                    println("Pokemon couldn't be registered.\n")
                                }
                            }

                            "special" -> {
                                //Treat Special Pokémon.
                                println("Enter additional combat power: ")
                                // Read an integer input from the user.
                                // Assign the value to the additionalCombatPower variable if the user enters
                                // a valid integer, or default value 0 in case of invalid input or null.
                                val additionalCombatPower = readLine()?.toIntOrNull() ?: 0

                                // Create and register SpecialPokemon
                                val pokemon = SpecialPokemon(id, type, level, trainer, additionalCombatPower)
                                if (pokemonCenter.registerPokemon(pokemon)) {
                                    println("Pokemon:")
                                    println("\t${pokemon.returnInfoString()}")
                                    println("successfully registered!\n")
                                }else {
                                    println("Pokemon couldn't be registered.\n")
                                }
                            }

                            "legendary" -> {
                                //Treat Legendary Pokémon
                                println("Enter special attack: ")
                                val specialAttack = readLine() ?: ""
                                println("Enter hidden ability.: ")
                                val hiddenAbility = readLine() ?: ""

                                // Create and register LegendaryPokemon
                                val pokemon = LegendaryPokemon(id, type, level, trainer, specialAttack,hiddenAbility )
                                if (pokemonCenter.registerPokemon(pokemon)) {
                                    println("Pokemon:")
                                    println("\t${pokemon.returnInfoString()}")
                                    println("successfully registered!\n")
                                }else {
                                    println("Pokemon couldn't be registered.\n")
                                }
                            }
                            else -> {
                                println("Invalid Pokemon category! Please try again...")
                            }
                        }
                    }while (!(category in listOf("common", "special", "legendary")))

                }else println("You can't add more pokémons!\n")
            }
            2 -> {
                // List all registered Pokémon
                println("\nList of registered Pokemons: ")
                val listPokemon = pokemonCenter.listPokemon()
                for (p in listPokemon) {
                    println(p)
                }
            }
            3 -> {
                // Obtain detailed information about a specific Pokémon
                println("\nObteining detailed information...")
                println("\tEnter Pokemon id (with format - PXXXX):")
                var id = readLine() ?: ""
                while (!validFormat(id)) {
                    println("Invalid id format. Please try again...")
                    id = readLine()!!
                }

                if (pokemonCenter.assignedId(id))
                    println(pokemonCenter.pokemonInfo(id))
                else println("Pokémon '$id' doesn’t exist!\n")
            }
            4 -> {
                // Increase the level of a Pokémon
                println("\nIncrease the Pokémon's level")
                println("\tEnter Pokemon id (with format - PXXXX):")
                var id = readLine()?: ""
                while (!validFormat(id)) {
                    println("Invalid id format. Please try again...")
                    id = readLine()?: ""
                }

                if (pokemonCenter.assignedId(id)){
                    println("\tEnter quantity to increase:")
                    val quantity = readLine()?.toIntOrNull() ?: 0
                    if (pokemonCenter.increaseLevel(id, quantity)){
                        println("Pokemon's level increased with success!\n")
                    }
                    else{
                        println("Pokémon's level couldn't be increased!\n")
                    }
                }
                else println("Pokémon '$id' doesn’t exist!\n")
            }
            5 -> {
                // Decrease the level of a Pokémon
                println("\nDecreas the Pokémon's level")
                println("\tEnter Pokemon id (with format - PXXXX):")
                var id = readLine() ?: ""
                while (!validFormat(id)) {
                    println("Invalid id format. Please try again...")
                    id = readLine()?: ""
                }

                if (pokemonCenter.assignedId(id)) {
                    println("\tEnter quantity to decrease:")
                    val quantity = readLine()?.toIntOrNull() ?: 0
                    if (pokemonCenter.decreaseLevel(id, quantity)){
                        println("Pokemon's level decreased with success!\n")
                    }
                }
                else println("Pokémon '$id' doesn’t exist!\n")
            }
            6 -> {
                // Check the current level of a Pokémon
                println("\nCheck the Pokémon's level")
                println("\tEnter Pokemon id (with format - PXXXX):")
                var id = readLine() ?: ""
                while (!validFormat(id)) {
                    println("Invalid id format. Please try again...")
                    id = readLine()?: ""
                }

                if (pokemonCenter.assignedId(id)) {
                    val level = pokemonCenter.checkLevel(id)
                    if (level != -1){
                        println("Pokemon level = $level\n")
                    }else{
                        println("Pokémon '$id' doesn’t exist!\n")
                    }
                }
                else println("Pokémon '$id' doesn’t exist!\n")
            }
            7 ->  return // Exit the application
            else -> println("Invalid option! Please try again...")
        }
    }
}

// Function to check if the Pokémon ID format is valid
fun validFormat(sequens : String): Boolean{
    val pattern = Regex("P[0-9]{4}")
    return sequens.matches(pattern)
}

// Function to display the menu options
fun menu(){
    println("\nMENU:\n" +
            "1. Register a new Pokémon.\n" +
            "2. View a list of registered Pokémon (ID, name, and level).\n" +
            "3. Obtain detailed information about a specific Pokémon.\n" +
            "4. Increase the level of a Pokémon.\n" +
            "5. Decrease the level of a Pokémon.\n" +
            "6. Check the current level of a Pokémon.\n" +
            "7. Exit the application.\n" +
            "\tYour option: ")

}