package com.fitnest.network

object Endpoints {
    const val BASE_URL = "https://fitnestappgo.herokuapp.com/"

    object Flow : Group {
        override val name = "flow"
    }

    object Onboarding : Group {
        override val name = "onboarding"
    }

    object Registration : Group {
        override val name = "registration"
    }

    object PrivateArea : Group {
        override val name = "private-area/"

        const val DASHBOARD = "dashboard"
    }

    interface Group {
        val name: String
    }
}