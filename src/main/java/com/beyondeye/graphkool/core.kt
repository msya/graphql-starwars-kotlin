package com.beyondeye.graphkool

import graphql.GraphQL
import graphql.execution.ExecutionStrategy
import graphql.schema.*

/**
 * Created by daely on 12/8/2016.
 */
fun newGraphQL(schemaBuilder: GraphQLSchema.Builder, executionStrategy: ExecutionStrategy?=null) = GraphQL(schemaBuilder.build(),executionStrategy)
fun newGraphQL(schema: GraphQLSchema, executionStrategy: ExecutionStrategy?=null) = GraphQL(schema,executionStrategy)
fun newObject(name:String)= GraphQLObjectType.newObject().name(name)
fun newEnum(name:String)= GraphQLEnumType.newEnum().name(name)
fun newInterface(name:String)=GraphQLInterfaceType.newInterface().name(name)
infix fun String.ofType(type_: GraphQLOutputType): GraphQLFieldDefinition.Builder {
    return GraphQLFieldDefinition.newFieldDefinition().type(type_).name(this)
}
operator fun String.rangeTo(type_: GraphQLOutputType) = this.ofType(type_)

fun GraphQLType.nonNull() = GraphQLNonNull(this)
operator fun GraphQLType.not() = GraphQLNonNull(this)

infix fun GraphQLFieldDefinition.Builder.staticValue(value:Any) = this.staticValue(value)
infix fun GraphQLFieldDefinition.Builder.description(d:String) = this.description(d)
infix fun <T> GraphQLFieldDefinition.Builder.dataFetcher(fetcher:DataFetcher<T>) = this.dataFetcher(fetcher)


fun newGraphQLSchema( query: GraphQLObjectType.Builder)= GraphQLSchema.newSchema().query(query)

fun listOfRefs(typeName:String)=GraphQLList(GraphQLTypeReference(typeName))
fun listOfObjs(wrappedType: GraphQLType) = GraphQLList(wrappedType)

infix fun GraphQLFieldDefinition.Builder.argument(builder: GraphQLArgument.Builder) = this.argument(builder)
infix fun GraphQLFieldDefinition.Builder.argument(newa: GQLArgumentBuilder) = this.argument(newa.build())

operator fun  String.unaryPlus()= GQLArgumentBuilder(this)
operator fun GQLArgumentBuilder.rangeTo(type_: GraphQLInputType) = this.type(type_)

class GQLArgumentBuilder(val name:String) {

    private var type: GraphQLInputType? = null
    private var defaultValue: Any? = null
    private var description: String? = null


    infix fun description(description: String): GQLArgumentBuilder {
        this.description = description
        return this
    }


    infix fun type(type: GraphQLInputType): GQLArgumentBuilder {
        this.type = type
        return this
    }

    infix fun defaultValue(defaultValue: Any): GQLArgumentBuilder {
        this.defaultValue = defaultValue
        return this
    }

    fun build(): GraphQLArgument {
        return GraphQLArgument(name, description, type, defaultValue)
    }
}