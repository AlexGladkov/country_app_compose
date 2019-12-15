package com.agladkov.composeexample.screens.home

import android.widget.Toast
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.Button
import com.agladkov.composeexample.MainContentModel
import com.agladkov.composeexample.cells.drawCell
import com.agladkov.composeexample.navigation.ScreenModel

@Composable
fun HomeScreen(navigateTo: (ScreenModel) -> Unit) {
    val homeViewModel = HomeViewModel()

    when (homeViewModel.state.viewState) {
        is CountryViewState.Progress -> Text(text = "Loading...")
        is CountryViewState.Empty -> MoreButton(homeViewModel = homeViewModel)
        is CountryViewState.ShowContent -> {
            VerticalScroller {
                Column(
                    crossAxisSize = LayoutSize.Expand,
                    crossAxisAlignment = CrossAxisAlignment.Stretch,
                    mainAxisAlignment = MainAxisAlignment.Start,
                    mainAxisSize = LayoutSize.Expand
                ) {
                    HeightSpacer(16.dp)

                    (homeViewModel.state.viewState as? CountryViewState.ShowContent)?.let { state ->
                        drawList(data = state.data, navigateTo = navigateTo)
                    }
                }
            }
        }
    }
}

@Composable
fun MoreButton(homeViewModel: HomeViewModel) {
    Padding(16.dp) {
        Button(text = "Load data",
            onClick = {
                homeViewModel.fetchMore()
            })
    }
}

@Composable
fun drawList(data: List<MainContentModel>, navigateTo: (ScreenModel) -> Unit) {
    data.forEach { model ->
        Padding(left = 16.dp, top = 0.dp, bottom = 8.dp, right = 16.dp) {
            drawCell(model, onInfoClick = { model ->
                navigateTo(ScreenModel(key = "Info", data = model))
            }, onShareClick = { model ->
                navigateTo(ScreenModel(key = "Share", data = model))
            })
        }

        HeightSpacer(height = 16.dp)
    }
}
