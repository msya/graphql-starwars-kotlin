package com.graphql.starwars.server

import com.beyondeye.graphkool.newGraphQL
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.graphql.starwars.schema.StarWarsSchema
import graphql.ExecutionResult
import spark.Request
import spark.Spark.post
import java.io.IOException
import javax.xml.ws.http.HTTPException

/**
 * Exposes the POST endpoint "/graphql" where a query should be given in the body of
 * the response. The server will validate and return the results.
 */
object StarWarsServerApplication {

    /**
     * Mapper for Jackson.
     */
    val mapper = jacksonObjectMapper()

    /**
     * `GraphQL` object used to execute the query.
     */
    val graphQL = newGraphQL(StarWarsSchema.starWarsSchema)

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun main(args: Array<String>) {
        post("/graphql") { request, response ->
            val payload = getPayload(request)

            payload?.let {
                val query = payload["query"].toString()
                val variables = getVariables(payload)

                // Execute query and get data or any errors
                val executionResult = graphQL.execute(query, null, null, variables)
                val result = getResult(executionResult)

                response.type("application/json")
                mapper.writeValueAsString(result)
            }
        }
    }

    /**
     * Get payload from the request.
     */
    fun getPayload(request: Request): Map<String, Any>? {
        try {
            return mapper.readValue<Map<String, Any>>(request.body())
        } catch (e: IOException) {
            HTTPException(500)
            return null
        }
    }

    /**
     * Get any variables passed in the request.
     */
    @Suppress("UNCHECKED_CAST")
    fun getVariables(payload: Map<String, *>) =
            payload.getOrElse("variables") { emptyMap<String, Any>() } as Map<String, Any>

    /**
     * Get any errors or data from [executionResult].
     */
    fun getResult(executionResult: ExecutionResult): MutableMap<String, Any> {
        val result = mutableMapOf<String, Any>()
        when {
            executionResult.errors.isNotEmpty() ->
                result.put("errors", executionResult.errors)
            else ->
                result.put("data", executionResult.getData())
        }
        return result
    }

}
