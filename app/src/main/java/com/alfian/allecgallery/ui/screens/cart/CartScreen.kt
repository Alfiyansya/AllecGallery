package com.alfian.allecgallery.ui.screens.cart

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alfian.allecgallery.R
import com.alfian.allecgallery.ui.common.UiState
import com.alfian.allecgallery.ui.components.EmptyContentItem
import com.alfian.allecgallery.ui.components.OrderButton

@Composable
fun CartScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<CartViewModel>()

    val context = LocalContext.current
    viewModel.uiState.collectAsStateWithLifecycle(initialValue = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Error -> {}
            UiState.Loading -> viewModel.getOrderBouquetInCart()
            is UiState.Success -> {
                CartContent(
                    uiState.data,
                    context,
                    onProductCountChanged = { bouquetId, count ->
                        viewModel.updateOrderBouquet(bouquetId, count)
                    },
                    modifier = modifier
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CartContent(
    state: CartState,
    context: Context,
    onProductCountChanged: (Long, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.cart),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                    , fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        )
        if(state.orderBouquet.isNotEmpty()){
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(weight = 1f)
            ) {
                items(state.orderBouquet, key = {it.bouquetId}){item ->
                    CartItem(
                        bouquetId = item.bouquetId,
                        image = item.image,
                        name = item.name,
                        totalPrice = item.bouquetPrice * item.count,
                        count = item.count,
                        onProductCountChanged = onProductCountChanged
                    )
                    HorizontalDivider()
                }
            }
        }else{
            EmptyContentItem(cart = true)
        }
        OrderButton(
            text = stringResource(R.string.total_order, state.totalPrice),
            enabled = state.orderBouquet.isNotEmpty(),
            onClick = {
                Toast.makeText(context,"Pesanan anda ${state.totalPrice}", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.padding(16.dp)
        )
    }

}
