package com.elm.elmassignment.model.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.elm.elmassignment.model.FoodItem
import com.elm.elmassignment.model.response.FoodCategoriesResponse
import com.elm.elmassignment.model.response.IncidentListResponse
import com.elm.elmassignment.model.response.Incidents
import com.elm.elmassignment.model.response.MealsResponse
import com.elm.elmassignment.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IncidentRemoteSource @Inject constructor(private val incidentApi: IncidentApi) {

    private var cachedCategories: List<FoodItem>? = null
    private var cachedIncidents: List<Incidents>? = null

    suspend fun getFoodCategories(): List<FoodItem> = withContext(Dispatchers.IO) {
        var cachedCategories = cachedCategories
        if (cachedCategories == null) {
            cachedCategories = incidentApi.getFoodCategories().mapCategoriesToItems()
            this@IncidentRemoteSource.cachedCategories = cachedCategories
        }
        return@withContext cachedCategories
    }

    suspend fun getIncidents(): List<Incidents> = withContext(Dispatchers.IO) {
        var cachedIncidents = cachedIncidents
        if (cachedIncidents == null) {
            cachedIncidents = incidentApi.getIncidents().mapIncidentsToItems()
            this@IncidentRemoteSource.cachedIncidents = cachedIncidents
        }
        return@withContext cachedIncidents
    }

    suspend fun getMealsByCategory(categoryId: String) = withContext(Dispatchers.IO) {
        val categoryName = getFoodCategories().first { it.id == categoryId }.name
        return@withContext incidentApi.getMealsByCategory(categoryName).mapMealsToItems()
    }

    private fun FoodCategoriesResponse.mapCategoriesToItems(): List<FoodItem> {
        return this.categories.map { category ->
            FoodItem(
                id = category.id,
                name = category.name,
                description = category.description,
                thumbnailUrl = category.thumbnailUrl
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun IncidentListResponse.mapIncidentsToItems(): List<Incidents> {
        return this.incidents.map { category ->
            Incidents(
                id = category.id,
                description = category.description,
                latitude = category.latitude,
                longitude = category.longitude,
                createdAt = category.createdAt,
                updatedAt = category.updatedAt,
                medias = category.medias
            )
        }
    }

    private fun MealsResponse.mapMealsToItems(): List<FoodItem> {
        return this.meals.map { category ->
            FoodItem(
                id = category.id,
                name = category.name,
                thumbnailUrl = category.thumbnailUrl
            )
        }
    }

}