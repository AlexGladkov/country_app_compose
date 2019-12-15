package com.agladkov.composeexample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import com.agladkov.composeexample.cells.drawCell


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setContent { myApp() }
    }

    @Composable
    fun myApp() {
        MaterialTheme {
            Log.e("TAG", "state ${viewModel.state.viewState}")
            when (viewModel.state.viewState) {
                is CountryViewState.Progress -> Text(text = "Loading...")
                is CountryViewState.Empty -> MoreButton()
                is CountryViewState.ShowContent -> {
                    VerticalScroller {
                        Column(
                            crossAxisSize = LayoutSize.Expand,
                            crossAxisAlignment = CrossAxisAlignment.Stretch,
                            mainAxisAlignment = MainAxisAlignment.Start,
                            mainAxisSize = LayoutSize.Expand
                        ) {
                            HeightSpacer(16.dp)

                            (viewModel.state.viewState as? CountryViewState.ShowContent)?.let { state ->
                                drawList(state.data)
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun MoreButton() {
        Padding(16.dp) {
            Button(text = "Load data",
                onClick = {
                    viewModel.fetchMore()
                })
        }
    }

    @Composable
    fun drawList(data: List<MainContentModel>) {
        data.forEach { model ->
            Padding(left = 16.dp, top = 0.dp, bottom = 8.dp, right = 16.dp) {
                drawCell(model, onInfoClick = { model ->
                    Toast.makeText(
                        applicationContext,
                        "${model.title} info click",
                        Toast.LENGTH_SHORT
                    ).show()
                }, onShareClick = { model ->
                    Toast.makeText(
                        applicationContext,
                        "${model.title} share click",
                        Toast.LENGTH_SHORT
                    ).show()
                })
            }

            HeightSpacer(height = 16.dp)
        }
    }
}
