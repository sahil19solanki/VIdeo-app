package com.android.videoapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.media3.common.MediaItem
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import cafe.adriel.voyager.core.screen.Screen
import coil.compose.AsyncImage
import com.android.videoapp.domian.model.Video

data class DetailScreen(val video:Video) : Screen {
    @Composable
    override fun Content() {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        var lifecycle by remember {
            mutableStateOf(Lifecycle.Event.ON_CREATE)
        }
        val mediaItem =
            MediaItem.fromUri(video.url)
        val context = LocalContext.current
        val exoPlayer = remember{
            ExoPlayer.Builder(context).build().apply {
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = true
            }
        }
        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(key1 = lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                lifecycle = event
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                exoPlayer.release()
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }



        Column {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 11f)
                    .fillMaxWidth()
                    .height(screenHeight * 0.25f),
                factory = {
                    PlayerView(context).also { playerView ->
                        playerView.player = exoPlayer
                    }
                },
                update = {
                    when (lifecycle) {
                        Lifecycle.Event.ON_RESUME -> {
                            it.onResume()
                            it.player?.play()
                        }
                        Lifecycle.Event.ON_PAUSE -> {
                            it.onPause()
                            it.player?.pause()
                        }
                        else -> Unit
                    }
                }
            )

            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp)
            ) {
                Row {

                    AsyncImage(
                        model = video.profile,
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Column {
                        Text(text = video.title)
                        Text(
                            text = "${video.channel_name} - ${video.views} views - ${video.uploaded}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier
                .height(1.dp)
                .background(Color.Gray)
                .fillMaxWidth()
                .padding(horizontal = 8.dp))
            Box(modifier = Modifier.padding(16.dp)){
                Column {
                    Text(text = "Description")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = video.description, fontSize = 12.sp,color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f) )
                }

            }
        }
    }
}