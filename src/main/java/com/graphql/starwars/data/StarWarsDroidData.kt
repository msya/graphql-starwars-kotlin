package com.graphql.starwars.data

import com.graphql.starwars.data.Episode.*

/**
 * Created by computer on 4/22/17.
 */
object StarWarsDroidData {

    val threepio = StarWarsCharacter(
            id = "2000",
            name = "C-3PO",
            height = 1.7272,
            friends = listOf("1000", "1002", "2001"),
            appearsIn = listOf(NEWHOPE, EMPIRE, JEDI),
            primaryFunction = "Protocol")

    val artoo = StarWarsCharacter(
            id = "2001",
            name = "R2-D2",
            height = 0.9144,
            friends = listOf("1000", "1002", "1003"),
            appearsIn = listOf(NEWHOPE, EMPIRE, JEDI),
            primaryFunction = "Astromech")

    val droidData = mapOf(
            "2000" to threepio,
            "2001" to artoo
    )

}
