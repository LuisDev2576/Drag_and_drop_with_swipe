package com.example.draganddropwithswipe.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.draganddropwithswipe.data.model.ItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private var _itemList by mutableStateOf(listOf(
            ItemModel("1", "Asunto 1", "Destinatario 1"),
            ItemModel("2", "Asunto 2", "Destinatario 2"),
            ItemModel("3", "Asunto 3", "Destinatario 3"),
            ItemModel("4", "Asunto 4", "Destinatario 4"),
            ItemModel("5", "Asunto 5", "Destinatario 5"),
            ItemModel("6", "Asunto 6", "Destinatario 6"),
            ItemModel("7", "Asunto 7", "Destinatario 7"),
            ItemModel("8", "Asunto 8", "Destinatario 8"),
            ItemModel("9", "Asunto 9", "Destinatario 9"),
            ItemModel("10", "Asunto 10", "Destinatario 10"),
            ItemModel("11", "Asunto 11", "Destinatario 11"),
            ItemModel("12", "Asunto 12", "Destinatario 12"),
            ItemModel("13", "Asunto 13", "Destinatario 13"),
            ItemModel("14", "Asunto 14", "Destinatario 14"),
            ItemModel("15", "Asunto 15", "Destinatario 15")
        ))
    val itemList get() = _itemList

    fun removeItemById(id: String) {
        _itemList = _itemList.filter { it.id != id }
    }
}
