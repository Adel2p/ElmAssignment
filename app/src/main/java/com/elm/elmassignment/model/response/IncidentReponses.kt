package com.elm.elmassignment.model.response

import com.google.gson.annotations.SerializedName

data class IncidentListResponse(
    @SerializedName("baseURL") var baseURL: String? = null,
    @SerializedName("incidents") var incidents: ArrayList<Incidents> = arrayListOf()
)

data class Medias(

    @SerializedName("id") var id: String? = null,
    @SerializedName("mimeType") var mimeType: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("type") var type: Int? = null,
    @SerializedName("incidentId") var incidentId: String? = null

)

data class Incidents(

    @SerializedName("id") var id: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("latitude") var latitude: Float? = null,
    @SerializedName("longitude") var longitude: Float? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("priority") var priority: String? = null,
    @SerializedName("typeId") var typeId: Int? = null,
    @SerializedName("issuerId") var issuerId: String? = null,
    @SerializedName("assigneeId") var assigneeId: String? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("medias") var medias: ArrayList<Medias> = arrayListOf()

)
