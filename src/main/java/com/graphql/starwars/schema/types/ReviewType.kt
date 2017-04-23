package com.graphql.starwars.schema.types

import graphql.Scalars.GraphQLInt
import graphql.Scalars.GraphQLString
import graphql.schema.GraphQLInputObjectField.newInputObjectField
import graphql.schema.GraphQLInputObjectType
import graphql.schema.GraphQLInputObjectType.newInputObject

/**
 * GraphQL input object type to add a review for a Star Wars film.
 */
val review: GraphQLInputObjectType =
        newInputObject()
                .name("Review")
                .field(newInputObjectField()
                        .name("stars")
                        .type(GraphQLInt))
                .field(newInputObjectField()
                        .name("commentary")
                        .type(GraphQLString))
                .build()
