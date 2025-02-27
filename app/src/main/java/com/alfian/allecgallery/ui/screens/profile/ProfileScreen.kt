package com.alfian.allecgallery.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alfian.allecgallery.R

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val profile = painterResource(R.drawable.profile)
    val name = stringResource(R.string.author_name)
    val email = stringResource(R.string.author_email)
    Column(modifier = modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .align(Alignment.CenterHorizontally)
                ){

                    Image(
                        painter = profile,
                        contentDescription = "Profile", modifier = Modifier
                        .size(200.dp)
                        .clip(
                            CircleShape
                        ))
                }


                Spacer(modifier = Modifier.height(16.dp))

                Text(text = name, style = MaterialTheme.typography.titleMedium)
                Text(text = email)
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        Toast.makeText(context,"Hi $name👋", Toast.LENGTH_SHORT).show()
                    },
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.cannon_pink),
                        contentColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ){
                    Text(
                        text = "Say hi to $name!",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }

    }
}