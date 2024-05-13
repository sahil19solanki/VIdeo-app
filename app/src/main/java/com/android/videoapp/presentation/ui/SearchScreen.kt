package com.android.videoapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.android.videoapp.domian.model.Video
import com.android.videoapp.presentation.ui.componants.SearchBar
import com.android.videoapp.presentation.ui.componants.VideoItem

class SearchScreen(val videoList: List<Video>, val navigator: Navigator) : Screen {
    @Composable
    override fun Content() {
        var searchQuery by remember { mutableStateOf("") }
        val filteredVideoList =
            videoList.filter { it.title.contains(searchQuery, ignoreCase = true) }

        Column {
            SearchBar(searchQuery = searchQuery, onSearchQueryChange = { searchQuery = it })
            VideoList(filteredVideoList)
        }
    }

    @Composable
    fun VideoList(videoList: List<Video>) {
        LazyColumn {
            items(videoList.size) { index ->
                // Display video item details
                VideoItem(video = videoList[index], navigator = navigator,36.dp)
            }
        }
    }
}