package com.elm.elmassignment.ui.theme.feature.categories

import com.elm.elmassignment.model.response.Incidents


class IncidentCategoriesContract {

    data class State(
        val incidents: List<Incidents> = listOf(),
        val isLoading: Boolean = false
    )

    sealed class Effect {
        object DataWasLoaded : Effect()
    }
}