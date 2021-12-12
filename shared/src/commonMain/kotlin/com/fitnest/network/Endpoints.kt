package com.fitnest.network

object Endpoints {
    const val BASE_URL = "https://fitnestappgo.herokuapp.com/"

    object Main : Group {
        override val name = "main"
    }

    object Onboarding : Group {
        override val name = "onboarding"
    }

    object Registration : Group {
        override val name = "registration"
    }

    interface Group {
        val name: String
    }
}