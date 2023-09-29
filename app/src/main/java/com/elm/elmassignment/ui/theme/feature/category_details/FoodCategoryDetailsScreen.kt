package com.elm.elmassignment.ui.theme.feature.category_details


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.elm.elmassignment.model.response.Incidents
import com.elm.elmassignment.ui.theme.feature.categories.FoodItemDetails


@Composable
fun FoodCategoryDetailsScreen(state: FoodCategoryDetailsContract.State) {
  //  val scrollState = rememberLazyListState()
  //  val scrollOffset: Float = min(
   //     1f,
    //    1 - (remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } } / 600f + remember { derivedStateOf { scrollState.firstVisibleItemIndex } })
//    )
//    Surface(color = MaterialTheme.colors.background) {
//        Column {
//            Surface(elevation = 4.dp) {
//                CategoryDetailsCollapsingToolbar(state.category, scrollOffset)
//            }
//            Spacer(modifier = Modifier.height(2.dp))
//            LazyColumn(
//                state = scrollState,
//                contentPadding = PaddingValues(bottom = 16.dp)
//            ) {
//                items(state.categoryFoodItems) { item ->
//                    FoodItemRow(
//                        item = item,
//                        iconTransformationBuilder = {
//                            transformations(
//                                CircleCropTransformation()
//                            )
//                        }
//                    )
//                }
//            }
//        }
//    }
}

@Composable
private fun CategoryDetailsCollapsingToolbar(
    category: Incidents?,
    scrollOffset: Float,
) {
    val imageSize by animateDpAsState(targetValue = max(72.dp, 128.dp * scrollOffset), label = "")
    Row {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = CircleShape,
            border = BorderStroke(
                width = 2.dp,
                color = Color.Black
            ),
            elevation = 4.dp
        ) {
//            Image(
//                painter = rememberImagePainter(
//                    data = category?.thumbnailUrl,
//                    builder = {
//                        transformations(CircleCropTransformation())
//                    },
//                ),
//                modifier = Modifier.size(max(72.dp, imageSize)),
//                contentDescription = "Food category thumbnail picture",
//            )
        }
        FoodItemDetails(
            item = category,
            modifier = Modifier
                .padding(
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
                .fillMaxWidth()
        )
    }
}