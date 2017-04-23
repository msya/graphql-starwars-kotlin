package com.graphql.starwars.data

/**
 * Episode type.
 */
enum class Episode {
    NEWHOPE,
    EMPIRE,
    JEDI
}

/**
 * Star Wars character data for human or droid.
 */
data class StarWarsCharacter(var id: String,
                             var name: String,
                             var height: Double,
                             var friends: List<String>,
                             var appearsIn: List<Episode>,
                             var homePlanet: String? = null,
                             var primaryFunction: String? = null)

/**
 * Star Wars movie review.
 */
data class Review(var star: Int,
                  var commentary: String)