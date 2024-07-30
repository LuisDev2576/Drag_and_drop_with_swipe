package com.example.draganddropwithswipe.presentation.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.PhoneInTalk
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.draganddropwithswipe.data.model.ItemModel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
){
    MyList(itemList = homeViewModel.itemList)
}

@Composable
fun MyList(
    itemList: List<ItemModel>
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(itemList){ item ->
            MyListItems(
                id = item.id,
                asunto = item.asunto,
                destinatario = item.destinatario
            )
        }
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun MyListItems(
    id: String,
    asunto: String,
    destinatario: String
){
    var bgColor by remember { mutableStateOf(Color.Magenta) }

    val color = animateColorAsState(
        targetValue = bgColor,
        animationSpec = tween(
            durationMillis = 2000
        )
    )

    val squareSize = 150.dp
    val swipeAbleState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1)
    val coroutine = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xfff2B51AA))
            .swipeable(
                state = swipeAbleState,
                anchors = anchors,
                thresholds = { _, _ ->
                    FractionalThreshold(0.3f)
                },
                orientation = Orientation.Horizontal
            )
    ) {

        // Contenido oculto
        Row(
            modifier = Modifier
                .width(150.dp)
                .height(109.dp)
        )  {
            Column(
                modifier = Modifier
                    .clickable {
                        coroutine.launch {
                            swipeAbleState.animateTo(0)
                        }
                    }
                    .background(Color(0xfffCE000F))
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(imageVector = Icons.Default.VolunteerActivism, contentDescription = null, tint = Color.White)
                Text(text = "Entregar", color = Color.White)
            }
            Column(
                modifier = Modifier
                    .clickable {
                        coroutine.launch {
                            swipeAbleState.animateTo(0)
                        }
                    }
                    .background(Color(0xfff2B51AA))
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(imageVector = Icons.Default.PinDrop, contentDescription = null, tint = Color.White)
                Text(text = "Mapa", color = Color.White)
            }
        }

        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        swipeAbleState.offset.value.roundToInt(), 0
                    )
                }
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(109.dp)
                .fillMaxHeight()
        ) {

            // Swipeable content
            val colorRed = Color(0xFFC32420)
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                border = BorderStroke(
                    1.dp,
                    Color.LightGray
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Box {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 16.dp)
                            .align(Alignment.Center),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        VerticalDivider(
                            thickness = 3.dp,
                            color = colorRed,
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .height(90.dp)
                                .clip(CircleShape)
                        )

                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = colorRed
                                ),
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .border(1.dp, Color.White, CircleShape)
                            ) {
                                Icon(modifier = Modifier.padding(3.dp), tint = Color.White, imageVector = Icons.Default.PhoneInTalk, contentDescription = "Phone in talk Icon")
                            }
                            VerticalDivider(
                                thickness = 2.dp,
                                color = Color.LightGray,
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .height(25.dp)
                            )
                            Card(
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = colorRed
                                ),
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .border(1.dp, Color.White, CircleShape)
                            ) {
                                Icon(modifier = Modifier.padding(3.dp), tint = Color.White, imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp, top = 16.dp, bottom = 16.dp, end = 8.dp)
                            .align(Alignment.Center),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier
                            .weight(1f)) {
                            Text(text = id, fontWeight = FontWeight.Bold)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "CER", color = Color.DarkGray, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(20.dp))
                                Icon(imageVector = Icons.Outlined.Mail, contentDescription = "Mail icon", tint = Color.DarkGray)
                            }
                            Text(text = asunto, color = Color.DarkGray)
                            Text(text = "Destinatarío: $destinatario", color = Color.DarkGray)
                        }
                        Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "Arrow Icon")
                    }


                }
            }

        }
    }


}

