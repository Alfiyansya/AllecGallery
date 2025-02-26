package com.alfian.allecgallery.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alfian.allecgallery.R
import com.alfian.allecgallery.ui.theme.AllecGalleryTheme

@Composable
fun ProductCounter(
    orderId: Long,
    orderCount: Int,
    onProductIncreased: (Long) -> Unit,
    onProductDecreased: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.size(width = 110.dp, height = 40.dp).padding(4.dp)
    ) {
        CounterButton(
            text = stringResource(R.string.minus_symbol),
            modifier = Modifier
                .weight(1f)
                .clickable {
                    onProductDecreased(orderId)
                }
        )
        Text(
            text = orderCount.toString(),
            modifier = Modifier
                .testTag("count")
                .weight(1f),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        CounterButton(
            text = stringResource(R.string.plus_symbol),
            modifier = Modifier
                .weight(1f)
                .clickable {
                    onProductIncreased(orderId)
                }
        )
    }

}

@Composable
fun CounterButton(text: String, modifier: Modifier) {
    Surface(
        shape = RoundedCornerShape(size = 18.dp),
        border = BorderStroke(1.dp, colorResource(id = R.color.cannon_pink)),
        color = Color.Transparent,
        contentColor = colorResource(id = R.color.cannon_pink),
        modifier = Modifier.size(30.dp)
    ) {
        Text(
            text = text,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun ProductCounterPreview(){
    AllecGalleryTheme {
        ProductCounter(
            orderId = 1,
            orderCount = 0,
            onProductIncreased = {},
            onProductDecreased = {}
        )
    }
}
@Preview
@Composable
fun CounterButtonPreview() {
    AllecGalleryTheme {
        CounterButton(stringResource(R.string.plus_symbol), modifier = Modifier)
    }
}