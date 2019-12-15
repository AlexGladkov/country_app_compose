package com.agladkov.composeexample.cells

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.engine.geometry.Shape
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.TextButtonStyle
import androidx.ui.material.surface.Card
import androidx.ui.material.themeShape
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import com.agladkov.composeexample.MainContentModel

@Composable
fun drawCell(
    model: MainContentModel,
    onInfoClick: ((MainContentModel) -> Unit)? = null,
    onShareClick: ((MainContentModel) -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(
            topLeft = 8.dp,
            topRight = 8.dp,
            bottomLeft = 8.dp,
            bottomRight = 8.dp
        ),
        elevation = 8.dp
    ) {
        Column {
            if (model.image > 0) {
                val image = +imageResource(model.image)
                Container(expanded = true, height = 180.dp) {
                    DrawImage(image)
                }
            }

            Padding(top = 16.dp, bottom = 2.dp, left = 16.dp, right = 16.dp) {
                Text(
                    model.title,
                    style = TextStyle(
                        color = Color.Black, fontWeight = FontWeight.W700,
                        fontSize = 16.sp
                    )
                )
            }

            Padding(bottom = 16.dp, left = 16.dp, right = 16.dp) {
                Text(
                    model.title, style = TextStyle(
                        color = Color.Gray, fontWeight = FontWeight.W400,
                        fontSize = 12.sp
                    )
                )
            }

            Padding(left = 16.dp, right = 16.dp, bottom = 16.dp) {
                Row(
                    crossAxisAlignment = CrossAxisAlignment.Stretch,
                    mainAxisAlignment = MainAxisAlignment.End,
                    mainAxisSize = LayoutSize.Expand
                ) {
                    Button(
                        onClick = {
                            onInfoClick?.invoke(model)
                        },
                        style = TextButtonStyle()
                    ) {
                        Text("Info")
                    }

                    WidthSpacer(width = 12.dp)

                    Button(
                        onClick = {
                            onShareClick?.invoke(model)
                        },
                        style = TextButtonStyle()
                    ) {
                        Text("Share")
                    }
                }
            }
        }
    }
}