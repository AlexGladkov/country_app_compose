package com.agladkov.composeexample.screens.home

import androidx.compose.Model
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agladkov.composeexample.MainContentModel
import com.agladkov.composeexample.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CountryState {
    var viewState: CountryViewState =
        CountryViewState.Empty
}

sealed class CountryViewState {
    object Progress : CountryViewState()
    class ShowContent(val data: List<MainContentModel>) : CountryViewState()
    object Empty : CountryViewState()
    class Error(val t: Throwable) : CountryViewState()
}

@Model
class HomeViewModel {

    val state: CountryState = CountryState()

    fun fetchMore() {
        state.viewState = CountryViewState.Progress
        kotlin.runCatching {
            delay(2000)
        }.fold(
            onSuccess = {
                state.viewState =
                    CountryViewState.ShowContent(
                        listOf(
                            MainContentModel(
                                title = "Washington",
                                image = R.drawable.washington
                            ),
                            MainContentModel(
                                title = "Moscow",
                                image = R.drawable.moscow
                            ),
                            MainContentModel(
                                title = "Washington",
                                image = R.drawable.washington
                            ),
                            MainContentModel(
                                title = "Moscow",
                                image = R.drawable.moscow
                            ),
                            MainContentModel(
                                title = "Washington",
                                image = R.drawable.washington
                            ),
                            MainContentModel(
                                title = "Moscow",
                                image = R.drawable.moscow
                            ),
                            MainContentModel(
                                title = "Washington",
                                image = R.drawable.washington
                            ),
                            MainContentModel(
                                title = "Moscow",
                                image = R.drawable.moscow
                            )
                        )
                    )
            }, onFailure = { e ->
                state.viewState =
                    CountryViewState.Error(
                        e
                    )
            }
        )
    }
}