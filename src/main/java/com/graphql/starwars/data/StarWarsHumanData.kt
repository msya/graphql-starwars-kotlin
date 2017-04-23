package com.graphql.starwars.data

import com.graphql.starwars.data.Episode.*

/**
 * Data on some of human characters in the Star Wars films.
 */
object StarWarsHumanData {

    val luke = StarWarsCharacter(
            id = "1000",
            name = "Luke SkyWalker",
            height = 1.7272,
            friends = listOf("1002", "1003", "2000", "2001"),
            appearsIn = listOf(NEWHOPE, EMPIRE, JEDI),
            homePlanet = "Tattoine")

    val vader = StarWarsCharacter(
            id = "1001",
            name = "Darth Vader",
            height = 1.7272,
            friends = listOf("1004"),
            appearsIn = listOf(NEWHOPE, EMPIRE, JEDI),
            homePlanet = "Tattoine")

    val han = StarWarsCharacter(
            id = "1002",
            name = "Han Solo",
            height = 1.7272,
            friends = listOf("1000", "1003", "2001"),
            appearsIn = listOf(NEWHOPE, EMPIRE, JEDI))

    val leia = StarWarsCharacter(
            id = "1003",
            name = "Lei Organa",
            height = 1.524,
            friends = listOf("1000", "1002", "2000", "2001"),
            appearsIn = listOf(NEWHOPE, EMPIRE, JEDI),
            homePlanet = "Alderaan")

    val tarkin = StarWarsCharacter(
            id = "1004",
            name = "Wilhuff Tarkin",
            height = 1.7272,
            friends = listOf("1001"),
            appearsIn = listOf(NEWHOPE))

    val humanData = mapOf(
            "1000" to luke,
            "1001" to vader,
            "1002" to han,
            "1003" to leia,
            "1004" to tarkin)

}
