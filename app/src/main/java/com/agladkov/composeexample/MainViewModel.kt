package com.agladkov.composeexample

import android.util.Log
import androidx.compose.Model
import androidx.compose.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Model
class CountryState {
    var viewState: CountryViewState = CountryViewState.Empty
}

sealed class CountryViewState {
    object Progress : CountryViewState()
    class ShowContent(val data: List<MainContentModel>) : CountryViewState()
    object Empty : CountryViewState()
    class Error(val t: Throwable) : CountryViewState()
}

class MainViewModel : ViewModel() {

    val state: CountryState = CountryState()

    fun fetchMore() {
        viewModelScope.launch {
            state.viewState = CountryViewState.Progress
            kotlin.runCatching {
                delay(2000)
            }.fold(
                onSuccess = {
                    state.viewState = CountryViewState.ShowContent(
                        listOf(
                            MainContentModel(title = "Washington", image = R.drawable.washington),
                            MainContentModel(title = "Moscow", image = R.drawable.moscow),
                            MainContentModel(title = "Washington", image = R.drawable.washington),
                            MainContentModel(title = "Moscow", image = R.drawable.moscow),
                            MainContentModel(title = "Washington", image = R.drawable.washington),
                            MainContentModel(title = "Moscow", image = R.drawable.moscow),
                            MainContentModel(title = "Washington", image = R.drawable.washington),
                            MainContentModel(title = "Moscow", image = R.drawable.moscow)
                        )
                    )
                }, onFailure = { e ->
                    state.viewState = CountryViewState.Error(e)
                }
            )
        }
    }
}