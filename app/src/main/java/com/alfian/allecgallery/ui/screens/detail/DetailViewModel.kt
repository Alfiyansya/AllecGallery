package com.alfian.allecgallery.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alfian.allecgallery.domain.model.Bouquet
import com.alfian.allecgallery.domain.model.OrderBouquet
import com.alfian.allecgallery.domain.repository.BouquetRepository
import com.alfian.allecgallery.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: BouquetRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderBouquet>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderBouquet>> get() = _uiState

    fun getBouquetById(bouquetId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderBouquetById(bouquetId))
        }
    }
    fun addToCart(bouquet: Bouquet, count: Int){
        viewModelScope.launch{
            repository.updateOrderBouquet(bouquet.bouquetId, count)
        }
    }
}