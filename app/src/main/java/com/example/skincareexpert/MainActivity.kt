package com.example.skincareexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                HomeScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    var cleanser by remember { mutableStateOf(true) }
    var vitaminC by remember { mutableStateOf(true) }
    var sunscreen by remember { mutableStateOf(false) }

    var cleansingBalm by remember { mutableStateOf(false) }
    var retinol by remember { mutableStateOf(false) }

    val completed = listOf(
        cleanser,
        vitaminC,
        sunscreen,
        cleansingBalm,
        retinol
    ).count { it }

    val progress = completed / 5f

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "SkinCare Genius",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }

    ) { padding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),

            verticalArrangement = Arrangement.spacedBy(14.dp)

        ) {

            // DATE

            item {

                Text(
                    text = "Sunday, October 4",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // PROGRESS

            item {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement =
                                Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = "Routine Progress",
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "${(progress * 100).toInt()}%"
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            // MORNING ROUTINE

            item {

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "Morning Routine (AM)",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            item {

                RoutineItem(
                    number = "1",
                    name = "Gentle Hydrating Cleanser",
                    checked = cleanser,
                    onCheckedChange = {
                        cleanser = it
                    }
                )

                RoutineItem(
                    number = "2",
                    name = "Vitamin C Serum",
                    checked = vitaminC,
                    onCheckedChange = {
                        vitaminC = it
                    }
                )

                RoutineItem(
                    number = "3",
                    name = "SPF 50+ Sunscreen",
                    checked = sunscreen,
                    onCheckedChange = {
                        sunscreen = it
                    }
                )
            }

            // EVENING ROUTINE

            item {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Evening Routine (PM)",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            item {

                RoutineItem(
                    number = "1",
                    name = "Cleansing Balm",
                    checked = cleansingBalm,
                    onCheckedChange = {
                        cleansingBalm = it
                    }
                )

                RoutineItem(
                    number = "2",
                    name = "Retinol 0.5% Night Cream",
                    checked = retinol,
                    onCheckedChange = {
                        retinol = it
                    }
                )
            }

            // ADD STEP

            item {

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text("+ Add Step")
                }
            }

            // BOTTOM NAVIGATION MOCKUP

            item {

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                HorizontalDivider()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),

                    horizontalArrangement =
                        Arrangement.SpaceAround
                ) {

                    Text(
                        text = "Home",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text("Shelf")

                    Text("Settings")
                }
            }
        }
    }
}

@Composable
fun RoutineItem(
    number: String,
    name: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),

        shape = RoundedCornerShape(14.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )

            Spacer(
                modifier = Modifier.width(5.dp)
            )

            Text(
                text = "$number. $name",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}