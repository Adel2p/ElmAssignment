package com.elm.elmassignment.ui.theme.feature.category_details


import com.elm.elmassignment.model.response.Incidents


class FoodCategoryDetailsContract {
    data class State(
        val category: Incidents?,
        val categoryFoodItems: List<Incidents>
    )
}