package com.elm.elmassignment.ui.theme.feature.categories

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elm.elmassignment.R
import com.elm.elmassignment.model.response.Incidents
import com.elm.elmassignment.ui.theme.ComposeSampleTheme
import com.elm.elmassignment.utils.NavScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FoodCategoriesScreen(
    navController: NavController?,
    state: IncidentCategoriesContract.State,
    effectFlow: Flow<IncidentCategoriesContract.Effect>?,
    onNavigationRequested: (item: Incidents) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    // Listen for side effects from the VM
    LaunchedEffect(effectFlow) {
        effectFlow?.onEach { effect ->
            if (effect is IncidentCategoriesContract.Effect.DataWasLoaded)
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Food categories are loaded.",
                    duration = SnackbarDuration.Short
                )
        }?.collect()
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CategoriesAppBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController?.navigate(NavScreen.AddEditIncidentScreen.route)
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Incident")
            }
        }
    ) {
        Box {
            FoodCategoriesList(foodItems = state.incidents) { itemId ->
                onNavigationRequested(itemId)
            }
            if (state.isLoading)
                LoadingBar()
        }

    }

}

@Composable
private fun CategoriesAppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Home,
                modifier = Modifier.padding(horizontal = 12.dp),
                contentDescription = "Action icon"
            )
        },
        title = { Text(stringResource(R.string.app_name)) },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
fun FoodCategoriesList(
    foodItems: List<Incidents>,
    onItemClicked: (item: Incidents) -> Unit = { }
) {
    Column() {

        LazyColumn(
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            items(foodItems) { item ->
                FoodItemRow(item = item, onItemClicked = onItemClicked)
            }
        }
    }
}

@Composable
fun FoodItemRow(
    item: Incidents,
    onItemClicked: (item: Incidents) -> Unit = { }
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable { onItemClicked(item) }
    ) {
        Row(modifier = Modifier.animateContentSize()) {

            FoodItemDetails(
                item = item,
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 24.dp,
                        bottom = 24.dp
                    )
                    .fillMaxWidth(0.80f)
                    .align(Alignment.CenterVertically)
            )

        }
    }
}

@Composable
fun FoodItemDetails(
    item: Incidents?,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = item?.description ?: "",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = ("create at: " + item?.createdAt) ?: "",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = ("updated at: " + item?.updatedAt) ?: "",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

    }
}


@Composable
fun LoadingBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeSampleTheme {
        FoodCategoriesScreen(null, IncidentCategoriesContract.State(), null, { })
    }

}



