package com.fitnest.network

object Endpoints {
    val BASE_URL = "https://fitnestappgo.herokuapp.com/"

    object Main : Group {
        override val name = "main"
    }

    interface Group {
        val name: String
    }
}