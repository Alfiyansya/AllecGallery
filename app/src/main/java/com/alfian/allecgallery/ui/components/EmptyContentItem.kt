package com.alfian.allecgallery.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.alfian.allecgallery.R

@Composable
fun EmptyContentItem(
    cart: Boolean = false
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (cart){
            Text(text = stringResource(R.string.no_data_in_cart))

        }else{
            Text(text = stringResource(R.string.no_data_item))
        }
    }
}