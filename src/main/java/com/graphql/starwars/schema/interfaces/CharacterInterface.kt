package com.graphql.starwars.schema.interfaces

import com.beyondeye.graphkool.*
import com.graphql.starwars.data.StarWarsDataUtils
import com.graphql.starwars.schema.enums.episodeNum
import com.graphql.starwars.schema.enums.unit
import graphql.Scalars.*
import graphql.schema.GraphQLInterfaceType

/**
 * GraphQL interface contains common fields for human and droid.
 */
val characterInterface: GraphQLInterfaceType =
        newInterface("Character")
                .description("A character in the Star Wars Trilogy")
                .field("id"
                        ..!GraphQLString
                        description "The id of the character.")
                .field("name"
                        ..GraphQLString
                        description "The name of the character")
                .field("height"
                        ..GraphQLFloat
                        argument (+"unit"..unit description "Meter or Foot")
                        description "The height of the person")
                .field("friends"
                        ..listOfRefs("Character")
                        description "The friends of the character, or an empty list if have none.")
                .field("appearsIn"
                        ..listOfObjs(episodeNum)
                        description "Which movies they appear in")
                .typeResolver(StarWarsDataUtils.characterTypeResolver)
                .build()
