package com.example.draganddropwithswipe.presentation.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.draganddropwithswipe.presentation.home.components.DragDropList

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
){
    DragDropList(
        items = homeViewModel.itemList,
        onMove = { fromIndex, toIndex ->
            homeViewModel.moveItem(fromIndex, toIndex)
        }
    )
}




