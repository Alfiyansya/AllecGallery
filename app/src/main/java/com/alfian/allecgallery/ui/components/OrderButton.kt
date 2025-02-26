package com.alfian.allecgallery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alfian.allecgallery.R
import com.alfian.allecgallery.ui.theme.AllecGalleryTheme

@Composable
fun OrderButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.cannon_pink),
            contentColor = Color.White),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)

            .semantics(mergeDescendants = true){
                contentDescription = "Order Button"
            }
    ){
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
@Composable
@Preview(showBackground = true)
fun OrderButtonPreview(){
    AllecGalleryTheme {
        OrderButton(
            text = "Order Bouquet",
            onClick = {}
        )
    }
}