package com.android.videoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.videoapp.ui.theme.VideoAppTheme
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VideoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VideoAppTheme {
        Greeting("Android")
    }
}

data class Video(
    val id: Int,
    val title: String,
    val description: String,
    val likes: Int,
    val videoUrl: String
)

val supabase = createSupabaseClient(
    supabaseUrl = "https://shdleemmalykgbghhmmt.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNoZGxlZW1tYWx5a2diZ2hobW10Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTUzNTQzMDAsImV4cCI6MjAzMDkzMDMwMH0.zwP42jzIHy3LtkrD1IIemBQSfJZ-uvDSQL6Mf559afc"
) {
    install(Postgrest)
}

