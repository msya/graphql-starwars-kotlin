package com.graphql.starwars.schema.types

import com.beyondeye.graphkool.description
import com.beyondeye.graphkool.newObject
import com.beyondeye.graphkool.rangeTo
import graphql.Scalars.GraphQLString
import graphql.schema.GraphQLObjectType

/**
 * Create review type.
 */
val createReview: GraphQLObjectType =
        newObject("Create Review")
                .field("stars"
                        ..GraphQLString
                        description "The number of stars for the movie.")
                .field("commentary"
                        ..GraphQLString
                        description "The review for the movie.")
                .build()
