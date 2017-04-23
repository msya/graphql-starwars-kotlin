package com.graphql.starwars.schema.enums

import com.beyondeye.graphkool.newEnum

/**
 * Height GraphQL Enum type.
 */

val unit = newEnum("Height")
        .value("FOOT")
        .value("METER")
        .build()
