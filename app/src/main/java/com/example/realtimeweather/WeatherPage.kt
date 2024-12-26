package com.example.realtimeweather

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realtimeweather.api.NetWorkResponse
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.realtimeweather.api.FutureWeatherResponse
import com.example.realtimeweather.api.WeatherResponse
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
    fun WeatherPage(viewModel: WeatherViewModel = viewModel()) {
        val weatherResult = viewModel.weatherDataResponse.observeAsState()
        val futureWeatherResult = viewModel.futureWeatherResponse.observeAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    val currentDate = after14DaysDateDisplay()
    var expanded by remember { mutableStateOf(false) } // Controls dropdown visibility
    val options = listOf("Iceland",
            "India",
            "Indonesia",
            "Iran",
            "Iraq",
            "Ireland",
             "Isle of Man")
    var selectedOption by remember { mutableStateOf("") } // Default selected option
    var width by remember { mutableIntStateOf(0) } // Track the width of the button

    var city by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(0xFF74898C))
            .size(100.dp)
            .verticalScroll(rememberScrollState()) // Enable horizontal scrolling
    ) {
    Box(
        modifier = Modifier
            .fillMaxSize() // Ensures the background covers the entire screen
            .background(color = Color(0xFF74898C)) // Replace with your desired color
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
           /*     OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = city,
                    maxLines = 1,
                    onValueChange = {
                        city = it
                    },
                    label = {
                        Text(text = "Search for any location", color = Color.White)
                    },
                            keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                            ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                )
                    , textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White, // Color when the field is focused
                    unfocusedBorderColor = Color.White, // Color when the field is not focused
                    cursorColor = Color.White // Cursor color
                )
                )*/


          /*
                val currentDate = after14DaysDateDisplay()

                IconButton(onClick = {
                    viewModel.getData(city)
                    viewModel.getFutureData(city,currentDate)
                    keyboardController?.hide()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search for any location",
                        tint = Color.White
                    )
                }*/


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp) // Ensure padding around the dropdown
                ) {
                    Column {
                        // Button or box to open dropdown
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 2.dp, // Border width
                                    color = Color.White, // Border color
                                    shape = RoundedCornerShape(8.dp) // Rounded corners for the border
                                ) // Ensure the box spans the full width
                                .onGloballyPositioned { coordinates ->
                                    width = coordinates.size.width // Capture width of the Box
                                }
                                .clickable { expanded = true }
                                .background(color = Color(0xFF74898C), shape = RoundedCornerShape(8.dp))
                                .padding(15.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween // Space between text and icon
                            ) {

                                Text(  text = selectedOption.ifEmpty { "Select country" }, // Show hint if no selection
                                    color = if (selectedOption.isEmpty()) Color.Black else Color.White ) // Selected item text

                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown, // Dropdown arrow icon
                                    contentDescription = "Dropdown Icon",
                                    tint = Color.White // Icon color
                                )
                            }
                        }

                        // DropdownMenu with dynamic width matching the trigger box
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .width(with(LocalDensity.current) { width.toDp() }) // Set the width to match button's width
                                .padding(horizontal = 16.dp) // Apply horizontal margin to the dropdown
                        ) {
                            options.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(text = option) },
                                    onClick = {
                                        selectedOption = option // Update selected option
                                        if(selectedOption.isNotEmpty()){
                                            viewModel.getData(selectedOption)
                                            viewModel.getFutureData(selectedOption,currentDate)
                                        }
                                        expanded = false // Close dropdown
                                    }
                                )
                            }
                        }
                    }
                }
            }
            when (val result = weatherResult.value) {
                is NetWorkResponse.Error -> {
                    Text(text = result.message)
                }

                NetWorkResponse.Loading -> {
                    CircularProgressIndicator()
                }

                is NetWorkResponse.Success -> {
                    WeatherDetail(result.data)
                }

                null -> {}
            }
            when (val result = futureWeatherResult.value) {
                is NetWorkResponse.Error -> {
                    Text(text = result.message)
                }

                NetWorkResponse.Loading -> {
                    CircularProgressIndicator()
                }

                is NetWorkResponse.Success -> {
                    HorizontalScrollBoxes(result.data)
                }

                null -> {}
            }
        }
    }}

}

@Preview
@Composable
fun WeatherDetail(data: WeatherResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location icon",
                modifier = Modifier.size(40.dp),Color.Green,
            )
            Text(text = data.location.name.toString()+" ,", fontSize = 30.sp, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = data.location.country.toString(), fontSize = 18.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = data.current.temp_c.toString()+"°",
            fontSize = 80.sp, /*fontWeight = FontWeight.SemiBold,*/
            textAlign = TextAlign.Center,
            color = Color.White,
          //  modifier = Modifier.background(color = Color(0xFF44575A), shape = RoundedCornerShape(8.dp)).padding(16.dp)
              )
        Text(
            text = data.current.condition?.text.toString(),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        AsyncImage(modifier = Modifier.size(130.dp),
            model = "https:${data.current.condition?.icon}".replace("64x64","128x128"),
            contentDescription = "Condition icon"
        )

       //HorizontalScrollBoxes()

        Spacer(modifier = Modifier.height(16.dp))
        Card{
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF44575A), shape = RoundedCornerShape(8.dp)),) {
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround){
                    WeatherKeyValue("Humidity",data.current.humidity.toString(),Modifier.weight(1F))
                    WeatherKeyValue(
                        "Wind speed",
                        data.current.wind_kph.toString()+" km/h",
                        Modifier.weight(1F)
                    )
                }
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround){
                    WeatherKeyValue("uv", data.current.uv.toString(), Modifier.weight(1F))
                    WeatherKeyValue(
                        "Wind direction",
                        data.current.wind_dir.toString(),
                        Modifier.weight(1F)
                    )
                }
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround){
                    WeatherKeyValue(
                        "Local time",
                        data.location.localtime.split(" ")[1],
                        Modifier.weight(1F)
                    )
                    WeatherKeyValue(
                        "Local date",
                        data.location.localtime.split(" ")[0],
                        Modifier.weight(1F)
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherKeyValue(key: String, value: String, modifier: Modifier){
    Column(modifier = modifier.padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = key, color = Color.White,fontSize = 14.sp)
        Text(text = value,color = Color.White,fontSize = 14.sp)
    }
}


@Composable
private fun HorizontalScrollBoxes(data: FutureWeatherResponse) {
    val currentTime = getCurrentHour()
    val itemWidth = 80.dp // Width for each hour column
    val allHours = data.forecast.forecastday
        .flatMap { it.hour }
        .filter { it.time.split(" ")[1].split(":")[0] >= currentTime }
    val scrollableWidth = allHours.size * itemWidth.value
    Card {
        Row(
            modifier = Modifier
                .width(scrollableWidth.dp)
                .background(color = Color(0xFF44575A))
                .size(100.dp)
                .horizontalScroll(rememberScrollState()) // Enable horizontal scrolling
        ) {
            allHours.forEach { timeDate ->
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (timeDate.time.split(" ")[1].split(":")[0] == currentTime.split(
                            ":"
                        )[0]
                    ) {
                        Text(
                            text = "Now",
                            modifier = Modifier
                                .padding(2.dp)
                                .background(color = Color(0xFF44575A))
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            color = Color.White,
                            fontSize = 12.sp,
                        )
                    } else {
                        Text(
                            text = timeDate.time.split(" ")[1],
                            modifier = Modifier
                                .padding(2.dp)
                                .background(color = Color(0xFF44575A))
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            color = Color.White,
                            fontSize = 12.sp,
                        )
                    }

                    AsyncImage(
                        modifier = Modifier.size(18.dp),
                        model = "https:${timeDate.condition.icon}",
                        contentDescription = "Condition icon"
                    )
                    Text(
                        text = timeDate.temp_c + "°",
                        modifier = Modifier
                            .padding(2.dp)
                            .background(color = Color(0xFF44575A))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        color = Color.White,
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }
}

@Composable
fun SimpleDropdownMenu(weatherViewModel: WeatherViewModel) {
    val weatherResult = weatherViewModel.weatherDataResponse.observeAsState()
    val futureWeatherResult = weatherViewModel.futureWeatherResponse.observeAsState()
    val currentDate = after14DaysDateDisplay()
    var expanded by remember { mutableStateOf(false) } // Controls dropdown visibility
    val options = listOf("Option 1", "Option 2", "Option 3")
    var selectedOption by remember { mutableStateOf("") } // Default selected option
    var width by remember { mutableIntStateOf(0) } // Track the width of the button

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Ensure padding around the dropdown
    ) {
        Column {
            // Button or box to open dropdown
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp, // Border width
                        color = Color.White, // Border color
                        shape = RoundedCornerShape(8.dp) // Rounded corners for the border
                    ) // Ensure the box spans the full width
                    .onGloballyPositioned { coordinates ->
                        width = coordinates.size.width // Capture width of the Box
                    }
                    .clickable { expanded = true }
                    .background(color = Color(0xFF74898C), shape = RoundedCornerShape(8.dp))
                    .padding(15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // Space between text and icon
                ) {

                        Text(  text = selectedOption.ifEmpty { "Select country" }, // Show hint if no selection
                            color = if (selectedOption.isEmpty()) Color.Black else Color.White ) // Selected item text


                    Icon(
                        imageVector = Icons.Default.ArrowDropDown, // Dropdown arrow icon
                        contentDescription = "Dropdown Icon",
                        tint = Color.White // Icon color
                    )
                }
            }

            // DropdownMenu with dynamic width matching the trigger box
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { width.toDp() }) // Set the width to match button's width
                    .padding(horizontal = 16.dp) // Apply horizontal margin to the dropdown
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            selectedOption = option // Update selected option
                            if(selectedOption.isNotEmpty()){
                                weatherViewModel.getData(selectedOption)
                                weatherViewModel.getFutureData(selectedOption,currentDate)
                            }
                            expanded = false // Close dropdown
                        }
                    )
                }
            }

        }
    }

}



fun getCurrentHour(): String {
    val dateFormat = SimpleDateFormat("HH", Locale.getDefault())
    return dateFormat.format(Date())
}
@Composable
fun after14DaysDateDisplay(): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // Customize the pattern
    val after14Days = currentDate.plusDays(14)
    return after14Days.format(formatter)
}

