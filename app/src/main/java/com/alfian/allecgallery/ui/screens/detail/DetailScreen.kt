package com.alfian.allecgallery.ui.screens.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alfian.allecgallery.R
import com.alfian.allecgallery.ui.common.UiState
import com.alfian.allecgallery.ui.components.OrderButton
import com.alfian.allecgallery.ui.components.ProductCounter
import com.alfian.allecgallery.ui.theme.AllecGalleryTheme

@Composable
fun DetailScreen(
    bouquetId: Long,
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit,
) {
    val viewModel: DetailViewModel = hiltViewModel<DetailViewModel>()
    viewModel.uiState.collectAsStateWithLifecycle(initialValue = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Error -> {
            }

            UiState.Loading -> {
                viewModel.getBouquetById(bouquetId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    image = data.image,
                    name = data.name,
                    bouquetPrice = data.bouquetPrice,
                    description = data.description,
                    count = data.count,
                    onBackClick = navigateBack,
                    onAddToCart = { count ->
                        viewModel.addToCart(data, count)
                        navigateToCart()
                    }
                )
            }
        }
    }


}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    name: String,
    bouquetPrice: Int,
    description: String,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var totalPoint by rememberSaveable { mutableStateOf(0) }
    var orderCount by rememberSaveable { mutableStateOf(count) }

    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier.padding(8.dp)
                    .size(35.dp)
                    .clickable { onBackClick() }
            )
            Text(
                text = stringResource(R.string.detail_bouquet),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(2f)
            )
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier.height(400.dp)
                        .fillMaxWidth()
                )

            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = stringResource(R.string.bouquet_price, bouquetPrice),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify
                )
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(4.dp).background(Color.LightGray))
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ProductCounter(
                orderId = 1,
                orderCount = orderCount,
                onProductIncreased = { orderCount++ },
                onProductDecreased = { if (orderCount > 0) orderCount-- },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 16.dp)
            )
            totalPoint = bouquetPrice * orderCount
            OrderButton(
                text = stringResource(R.string.add_to_cart, totalPoint),
                enabled = orderCount > 0,
                onClick = {
                    onAddToCart(orderCount)
                }
            )
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    AllecGalleryTheme {
        DetailContent(
            name = "Bouquet Bunga Pink Putih Sedang",
            image = R.drawable.bouquet2,
            description = "Bouquet Bunga Pink Putih Sedang adalah sebuah buket berukuran sedang" +
                    " yang berisi beberapa bunga berwarna pink dan putih",
            bouquetPrice = 50000,
            count = 0,
            onBackClick = {},
            onAddToCart = {}
        )
    }
}
