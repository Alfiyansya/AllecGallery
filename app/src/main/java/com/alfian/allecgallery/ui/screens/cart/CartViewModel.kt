package com.alfian.allecgallery.ui.screens.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfian.allecgallery.domain.repository.BouquetRepository
import com.alfian.allecgallery.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: BouquetRepository): ViewModel(){
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>> get() = _uiState

    fun getOrderBouquetInCart(){

        viewModelScope.launch {

            _uiState.value = UiState.Loading
            repository.getOrderBouquet()
                .collect{ orderBouquet ->
                    val totalPrice =
                        orderBouquet.sumOf { it.bouquet.bouquetPrice * it.count }
                    _uiState.value = UiState.Success(CartState(orderBouquet,totalPrice))
                }
        }
    }

    fun updateOrderBouquet(bouquetId: Long, count: Int){
        viewModelScope.launch {
            repository.updateOrderBouquet(bouquetId, count)
                .collect{ isUpdated ->
                    if (isUpdated){
                        getOrderBouquetInCart()
                    }
                }
        }
    }
}