package com.elm.elmassignment.model.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.elm.elmassignment.model.response.IncidentListResponse
import com.elm.elmassignment.model.response.Incidents
import com.elm.elmassignment.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IncidentRemoteSource @Inject constructor(private val incidentApi: IncidentApi) {

    private var cachedIncidents: List<Incidents>? = null


    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getIncidents(): List<Incidents> = withContext(Dispatchers.IO) {
        var cachedIncidents = cachedIncidents
        if (cachedIncidents == null) {
            cachedIncidents = incidentApi.getIncidents().mapIncidentsToItems()
            this@IncidentRemoteSource.cachedIncidents = cachedIncidents
        }
        return@withContext cachedIncidents
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun IncidentListResponse.mapIncidentsToItems(): List<Incidents> {
        return this.incidents.map { category ->
            Incidents(
                id = category.id,
                description = category.description,
                latitude = category.latitude,
                longitude = category.longitude,
                createdAt = Utils.parseIncidentDate(category.createdAt),
                updatedAt = Utils.parseIncidentDate(category.updatedAt),
                medias = category.medias
            )
        }
    }

}