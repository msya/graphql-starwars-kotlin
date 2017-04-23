package com.graphql.starwars.schema.types

import com.beyondeye.graphkool.*
import com.graphql.starwars.data.StarWarsDataUtils
import com.graphql.starwars.schema.enums.episodeNum
import com.graphql.starwars.schema.enums.unit
import com.graphql.starwars.schema.interfaces.characterInterface
import graphql.Scalars.GraphQLFloat
import graphql.Scalars.GraphQLString
import graphql.schema.GraphQLObjectType

/**
 * Human GraphQL schema object.
 */
val humanType: GraphQLObjectType =
        newObject("Human")
                .description("A humanoid creature in the Star Wars universe.")
                .withInterface(characterInterface)
                .field("id"
                        ..!GraphQLString
                        description "The id of the human.")
                .field("name"
                        ..!GraphQLString
                        description "The name of the human.")
                .field("height"
                        ..!GraphQLFloat
                        argument (+"unit"..unit description "Meter or Foot")
                        description "The height of the person"
                        dataFetcher StarWarsDataUtils.heightDataFetcher)
                .field("friends"
                        ..listOfRefs("Character")
                        description "The friends of the character, or an empty list if have none."
                        dataFetcher StarWarsDataUtils.friendsDataFetcher)
                .field("appearsIn"
                        ..listOfObjs(episodeNum)
                        description "Which movies they appear in")
                .field("homePlanet"
                        ..GraphQLString
                        description "The home planet of the human or null unknown.")
                .build()
