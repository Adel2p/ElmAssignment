package com.elm.elmassignment.utils

sealed class NavScreen(val route: String) {
    object IncidentsScreen : NavScreen("incidents_screen")
    object AddEditIncidentScreen : NavScreen("add_edit_incident_screen")
    object DetailsIncidentScreen : NavScreen("details_incident_screen")

}