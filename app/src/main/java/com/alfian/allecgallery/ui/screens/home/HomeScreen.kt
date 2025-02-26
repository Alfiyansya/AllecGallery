package com.alfian.allecgallery.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alfian.allecgallery.domain.model.Bouquet
import com.alfian.allecgallery.ui.common.UiState
import com.alfian.allecgallery.ui.components.BouquetItem
import com.alfian.allecgallery.ui.components.EmptyContentItem
import com.alfian.allecgallery.ui.components.SearchTopBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
){
    val homeViewModel =  hiltViewModel<HomeViewModel>()
    val query by homeViewModel.query

    homeViewModel.uiState.collectAsStateWithLifecycle(initialValue = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Error ->{}
            UiState.Loading ->{
                homeViewModel.getAllBouquets()
            }
            is UiState.Success -> {
                HomeContent(
                    orderBouquet = uiState.data,
                    navigateToDetail = navigateToDetail,
                    query = query,
                    onQueryChange = homeViewModel::search,
                    modifier = modifier,
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    orderBouquet: List<Bouquet>,
    navigateToDetail: (Long) -> Unit,
    query: String,
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit
){
    Column {
        SearchTopBar(
            query = query,
            onQueryChange = onQueryChange
        )
        if(orderBouquet.isNotEmpty()){
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier.testTag("BouquetList")
            ) {
                items(
                    orderBouquet, key ={it.name}){ data ->
                    BouquetItem(
                        image = data.image,
                        name = data.name,
                        bouquetPrice = data.bouquetPrice,
                        modifier = Modifier.clickable {
                            navigateToDetail(data.bouquetId)
                        }
                    )
                }

            }
        }else{
            EmptyContentItem()
        }
    }
}