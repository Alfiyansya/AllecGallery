package com.alfian.allecgallery.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfian.allecgallery.domain.model.OrderBouquet
import com.alfian.allecgallery.domain.repository.BouquetRepository
import com.alfian.allecgallery.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: BouquetRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<OrderBouquet>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderBouquet>>> = _uiState

//    private var _searchBarState = mutableStateOf(SearchBarState.CLOSED)
//    val searchBarState: State<SearchBarState> get() = _searchBarState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.searchBouquet(query = newQuery)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { bouquetList ->
                    _uiState.value = UiState.Success(bouquetList)
                }
        }
//        _uiState.value = UiState.Success(bouquetList)

    }
//    init {
//        viewModelScope.launch {
//            repository.addInitialBouquets()
//        }
//    }
//            repository.getAllBouquets()
//                .catch {
//                    _uiState.value = UiState.Error(it.message.toString())
//                }
//                .collect{ bouquetList ->
//                    _uiState.value = UiState.Success(bouquetList)
//            }
//        }
//    }

    fun getAllBouquets() {
        viewModelScope.launch {
            repository.getAllBouquets()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { bouquetList ->
                    _uiState.value = UiState.Success(bouquetList)
                }
        }
    }

}