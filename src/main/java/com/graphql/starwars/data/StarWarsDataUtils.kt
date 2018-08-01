package com.graphql.starwars.data

import com.graphql.starwars.data.StarWarsDroidData.artoo
import com.graphql.starwars.data.StarWarsDroidData.droidData
import com.graphql.starwars.data.StarWarsHumanData.humanData
import com.graphql.starwars.data.StarWarsHumanData.luke
import com.graphql.starwars.schema.enums.unit
import com.graphql.starwars.schema.types.droidType
import com.graphql.starwars.schema.types.humanType
import graphql.schema.DataFetcher
import graphql.schema.TypeResolver

/**
 * Utility class to get Star Wars data.
 */
object StarWarsDataUtils {

    /**
     * Get data on human or droid based on [id].
     */
    fun getCharacter(id: String) =
            when {
                humanData[id] != null -> humanData[id]
                droidData[id] != null -> droidData[id]
                else -> null
            }

    /**
     * Data fetcher to get human data from the array [humanData] for
     * a particular id that is supplied as an argument.
     */
    val humanDataFetcher = DataFetcher {
        humanData[it.arguments["id"]]
    }

    /**
     * Data fetcher to get droid data from the array [droidData] for
     * a particular is that is supplied as an argument.
     */
    val droidDataFetcher = DataFetcher {
        droidData[it.arguments["id"]]
    }

    /**
     * Get friends of a character.
     */
    val friendsDataFetcher = DataFetcher {
        (it.getSource<StarWarsCharacter>())
                .friends
                .map { getCharacter(it) }
                .toList()
    }

    /**
     * Height data fetcher.
     */
    val heightDataFetcher = DataFetcher {
        val height = (it.getSource<StarWarsCharacter>()).height
        if (it.arguments["unit"] == unit.values[0].name) {
            height/0.3048
        }
        height
    }

    /**
     * Type resolver.
     */
    val characterTypeResolver = TypeResolver { /*it:TypeResolutionEnvironment! =>*/
        val o = it.getObject<StarWarsCharacter>()
        when {
            humanData.containsValue(o) -> humanType
            droidData.containsValue(o) -> droidType
            else -> null
        }
    }

    /**
     * Hero data fetcher.
     */
    val heroDataFetcher = DataFetcher {
        when {
            it.containsArgument("episode") && it.arguments["episode"] == Episode.EMPIRE ->
                luke
            else ->
                artoo
        }
    }

    /**
     * Review data fetcher.
     */
    val reviewDataFetcher = DataFetcher {
        val map = it.arguments["review"] as Map<*, *>
        Review(map["stars"] as Int, map["commentary"] as String)
    }

}
