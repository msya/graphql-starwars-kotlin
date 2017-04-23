package com.graphql.starwars.schema

import com.beyondeye.graphkool.*
import com.graphql.starwars.data.StarWarsDataUtils
import com.graphql.starwars.schema.enums.episodeNum
import com.graphql.starwars.schema.interfaces.characterInterface
import com.graphql.starwars.schema.types.createReview
import com.graphql.starwars.schema.types.droidType
import com.graphql.starwars.schema.types.humanType
import com.graphql.starwars.schema.types.review
import graphql.Scalars.GraphQLString
import graphql.schema.GraphQLObjectType
import graphql.schema.GraphQLSchema

/**
 * GraphQL schema for the Star Wars Data.
 */
object StarWarsSchema {

    /**
     * Query type.
     */
    val queryType: GraphQLObjectType.Builder =
            newObject("QueryType")
                    .field("hero"
                            ..characterInterface
                            argument (+"episode"..episodeNum description "If omitted, returns the hero of the whole saga. If provided, returns the hero of that particular episode.")
                            dataFetcher StarWarsDataUtils.heroDataFetcher)
                    .field("human"
                            ..humanType
                            argument (+"id"..!GraphQLString description "id of the human")
                            dataFetcher StarWarsDataUtils.humanDataFetcher)
                    .field("droid"
                            ..droidType
                            argument (+"id"..!GraphQLString description "id of the droid")
                            dataFetcher StarWarsDataUtils.droidDataFetcher)

    /**
     * Mutation type.
     */
    val mutation: GraphQLObjectType.Builder = newObject("Mutation")
            .field("createReview"..createReview
                    argument (+"ep"..episodeNum)
                    argument (+"review"..review)
                    dataFetcher StarWarsDataUtils.reviewDataFetcher)

    /**
     * Star wars schema.
     */
    val starWarsSchema: GraphQLSchema = newGraphQLSchema(queryType)
            .mutation(mutation)
            .build()

}
