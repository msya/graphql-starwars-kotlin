package com.graphql.starwars.schema.types

import com.beyondeye.graphkool.*
import com.graphql.starwars.data.StarWarsDataUtils
import com.graphql.starwars.schema.enums.episodeNum
import com.graphql.starwars.schema.enums.unit
import com.graphql.starwars.schema.interfaces.characterInterface
import graphql.Scalars.*
import graphql.schema.GraphQLObjectType

/**
 * Droid GraphQL schema object.
 */
val droidType: GraphQLObjectType =
        newObject("Droid")
                .description("A mechanical creature in the Star wars universe.")
                .withInterface(characterInterface)
                .field("id"
                        ..!GraphQLString
                        description "The id of the human.")
                .field("name"
                        ..GraphQLString
                        description "The name of the human.")
                .field("height"
                        ..GraphQLFloat
                        argument (+"unit"..unit description "Meter or Foot")
                        description "The height of the droid."
                        dataFetcher StarWarsDataUtils.heightDataFetcher)
                .field("friends"
                        ..listOfRefs("Character")
                        description "The friends of the character, or an empty list if have none."
                        dataFetcher StarWarsDataUtils.friendsDataFetcher)
                .field("appearsIn"
                        ..listOfObjs(episodeNum)
                        description "Which movies they appear in.")
                .field("primaryFunction"
                        ..GraphQLString
                        description "The primary function of the droid.")
                .build()
