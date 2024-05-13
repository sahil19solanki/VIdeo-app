package com.android.videoapp.presentation.ui

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.android.videoapp.presentation.VideoViewModel
import com.android.videoapp.presentation.ui.componants.VideoItem

class VideoListScreen(activity: ComponentActivity) : Screen {
    private val viewModel: VideoViewModel by activity.viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val videoState = viewModel.videoListState.collectAsState().value

        val navigator = LocalNavigator.current
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(text = "Video App")
                            Icon(imageVector = Icons.Default.Search,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .clickable { navigator?.push(SearchScreen(videoState.videoList,navigator)) })
                        }
                    }
                })

            },
        )
        {innerpadding ->
            Box(modifier = Modifier.padding(innerpadding)){
                if (videoState.isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (videoState.videoList.isNotEmpty()) {
                    LazyColumn {
                        items(videoState.videoList.size) { index ->
                            VideoItem(video = videoState.videoList[index], navigator = navigator!!,36.dp)
                        }
                    }
                }
            }
        }
    }

}