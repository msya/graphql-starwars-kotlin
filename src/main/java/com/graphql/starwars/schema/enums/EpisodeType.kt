package com.graphql.starwars.schema.enums

import com.beyondeye.graphkool.newEnum
import graphql.schema.GraphQLEnumType

/**
 * Episode type.
 */
val episodeNum: GraphQLEnumType = newEnum("Episode")
        .description("One of the films in the Star Wars Trilogy")
        .value("NEWHOPE", "Released in 1977")
        .value("EMPIRE", "Released in 1980")
        .value("JEDI", "Released in 1983")
        .build()
