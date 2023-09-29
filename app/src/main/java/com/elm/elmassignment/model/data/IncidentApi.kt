package com.elm.elmassignment.model.data

import com.elm.elmassignment.model.response.IncidentListResponse
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IncidentApi @Inject constructor(private val service: Service) {

    suspend fun getIncidents(): IncidentListResponse = service.getIncidents()

    interface Service {

        @GET("incident")
        suspend fun getIncidents(): IncidentListResponse

    }

    companion object {
        const val API_URL = "https://8d1e7f32-acd2-4063-93f5-88142acb5c15.mock.pstmn.io/"
    }
}


