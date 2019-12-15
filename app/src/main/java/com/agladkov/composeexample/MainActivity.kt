package com.agladkov.composeexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.ViewModelProviders
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import com.agladkov.composeexample.cells.drawCell
import com.agladkov.composeexample.screens.home.MainViewModel


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

        }
    }


}
