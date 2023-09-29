package com.elm.elmassignment.model.data

import com.elm.elmassignment.model.response.FoodCategoriesResponse
import com.elm.elmassignment.model.response.IncidentListResponse
import com.elm.elmassignment.model.response.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IncidentApi @Inject constructor(private val service: Service) {

    suspend fun getFoodCategories(): FoodCategoriesResponse = service.getFoodCategories()
    suspend fun getIncidents(): IncidentListResponse = service.getIncidents()
    suspend fun getMealsByCategory(categoryId: String): MealsResponse =
        service.getMealsByCategory(categoryId)

    interface Service {
        @GET("categories.php")
        suspend fun getFoodCategories(): FoodCategoriesResponse

        @GET("incident")
        suspend fun getIncidents(): IncidentListResponse

        @GET("filter.php")
        suspend fun getMealsByCategory(@Query("c") categoryId: String): MealsResponse
    }

    companion object {
        const val API_URL = "https://8d1e7f32-acd2-4063-93f5-88142acb5c15.mock.pstmn.io/"
    }
}


