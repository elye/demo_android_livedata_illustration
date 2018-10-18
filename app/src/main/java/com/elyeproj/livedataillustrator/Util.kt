package com.elyeproj.livedataillustrator

import java.util.*

fun IntRange.random() =
        (Random().nextInt((endInclusive + 1) - start) +  start)
                .toString()
                .padStart(4, '0')