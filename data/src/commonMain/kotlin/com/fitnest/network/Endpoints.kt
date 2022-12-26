package com.fitnest.network

object Endpoints {
    const val BASE_URL = "https://fitnestapp.onrender.com/"

    object Flow : Group {
        override val name = "flow"
    }

    object Onboarding : Group {
        override val name = "onboarding"
    }

    object Registration : Group {
        override val name = "registration"
    }

    object Auth : Group {
        override val name = "auth/"

        val LOGIN = "${name}login"
        val FORGET_PASSWORD = "${name}forget-password"
    }

    object PrivateArea : Group {
        override val name = "private-area/"

        val DASHBOARD = "${name}dashboard"
        val PROFILE = "${name}profile"

        object Notifications : Group {

            override val name = "${PrivateArea.name}notifications"

            val DEACTIVATE = "$name/deactivate"
            val PIN = "$name/pin"
            val DELETE = "$name/delete"
        }

        object ActivityTracker : Group {
            override val name = "${PrivateArea.name}activity-tracker"

            val DELETE_ACTIVITY = "$name/delete-activity"
            val ADD_ACTIVITY = "$name/add-activity"
        }
    }

    interface Group {
        val name: String
    }
}
