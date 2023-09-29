package com.elm.elmassignment.ui.theme.feature.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elm.elmassignment.model.data.IncidentRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncidentCategoriesViewModel @Inject constructor(private val remoteSource: IncidentRemoteSource) :
    ViewModel() {

    var state by mutableStateOf(
        IncidentCategoriesContract.State(
            incidents = listOf(),
            isLoading = true
        )
    )
        private set

    var effects = Channel<IncidentCategoriesContract.Effect>(UNLIMITED)
        private set

    init {
        viewModelScope.launch { getFoodCategories() }
    }

    private suspend fun getFoodCategories() {
        val incidents = remoteSource.getIncidents()
        viewModelScope.launch {
            state = state.copy(incidents = incidents, isLoading = false)
            effects.send(IncidentCategoriesContract.Effect.DataWasLoaded)
        }
    }
}



