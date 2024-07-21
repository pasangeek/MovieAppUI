package com.example.movieappui.ui

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappui.ui.theme.Gray
import com.example.movieappui.ui.theme.LightGray
import com.example.movieappui.ui.theme.Yellow
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import androidx.compose.foundation.layout.size
@Composable
fun SeatSelecterScreen(navController: NavController) {
    val today = LocalDate.now()
    val dateScrollState = rememberScrollState()
    val timeScrollState = rememberScrollState()

    val selectedSeat = remember {
        mutableStateListOf<String>()
    }

    val selectedDate = remember {
        mutableStateOf<LocalDate?>(null)
    }

    val selectedTime = remember {
        mutableStateOf<String?>(null)
    }

    Scaffold(

    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = 16.dp, vertical = 8.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back Button")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Select Seat", style = MaterialTheme.typography.headlineLarge)
            }
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 48.dp, top = 8.dp)
                    .background(color = Yellow)
                    .fillMaxWidth(0.5f),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Screen",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                )
            }
            /// seat mapping
            for (i in 1..6) {
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    for (j in 1..8) {
                        val seatNumber = "${(64 + i).toChar()}$j"
                        SeatComp(
                            isEnabled = i != 6,
                            isSelected = selectedSeat.contains(seatNumber),
                            seatNumber = seatNumber
                        ) { selected, seat ->
                            if (selected) {
                                selectedSeat.remove(seat)
                            } else {
                                selectedSeat.add(seat)
                            }
                        }

                        if (j != 8) Spacer(modifier = Modifier.width(if (j == 4) 16.dp else 8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(24.dp))
            /// indicator
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {

                SeatComp(isEnabled = false)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Reserved",
                    style = MaterialTheme.typography.headlineSmall,
                )

                Spacer(modifier = Modifier.width(16.dp))

                SeatComp(isEnabled = true, isSelected = true)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Selected",
                    style = MaterialTheme.typography.headlineSmall,
                )

                Spacer(modifier = Modifier.width(16.dp))

                SeatComp(isEnabled = true, isSelected = false)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Available",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Select Seat",
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Row(
                        modifier = Modifier.horizontalScroll(dateScrollState),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (i in 0..14) {
                            val date = today.plusDays(i.toLong())
                            DateComp(
                                date = date, isSelected = selectedDate.value == date
                            ) {
                                selectedDate.value = it
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.horizontalScroll(timeScrollState),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (i in 10..22 step 2) {
                            val time = "$i:00"
                            TimeComp(
                                time = time, isSelected = selectedTime.value == time
                            ) {
                                selectedTime.value = it
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(
                                text = "Total Price",
                                style = MaterialTheme.typography.titleSmall,
                            )
                            Text(
                                text = "\$${selectedSeat.size * 10}",
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }

                        Button(
                            modifier = Modifier
                                .wrapContentWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                 Yellow,
                            ),
                            shape = RoundedCornerShape(32.dp),
                            onClick = {},
                        ) {
                            Text("Continue")
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun TimeComp(
    time: String,
    isSelected: Boolean = false,
    onClick: (String) -> Unit = {},
) {
    val color = when {
        isSelected -> Yellow
        else -> Yellow.copy(alpha = 0.15f)
    }
    Surface(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onClick(time)
            }, shape = RoundedCornerShape(16.dp), color = color
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(12.dp),
        )
    }
}


@Composable
fun DateComp(
    date: LocalDate,
    isSelected: Boolean = false,
    onClick: (LocalDate) -> Unit = {},
) {
    val color = when {
        isSelected -> Yellow
        else -> Yellow.copy(alpha = 0.15f)
    }
    val textBg = when {
        isSelected -> Color.White
        else -> Color.Transparent
    }
    Surface(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onClick(date)
            }, shape = RoundedCornerShape(16.dp), color = color
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                style = MaterialTheme.typography.titleSmall
            )
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(textBg)

                    .padding(4.dp),
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Composable
fun SeatComp(
    isEnabled: Boolean = false,
    isSelected: Boolean = false,
    seatNumber: String = "",
    onClick: (Boolean, String) -> Unit = { _, _ -> },
) {
    val seatColor = when {
        !isEnabled -> Color.Gray
        isSelected -> Yellow
        else -> Color.White
    }

    val textColor = when {
        isSelected -> Color.White
        else -> Color.Black
    }

    Box(modifier = Modifier
        .size(32.dp)
        .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(8.dp))
        .clip(RoundedCornerShape(8.dp))
        .background(color = seatColor)
        .clickable {
            onClick(isSelected, seatNumber);
        }
        .padding(8.dp), contentAlignment = Alignment.Center) {
        Text(
            seatNumber,
            style = MaterialTheme.typography.titleSmall.copy(color = textColor),
        )
    }
}