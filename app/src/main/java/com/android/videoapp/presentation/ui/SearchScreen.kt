package com.android.videoapp.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.android.videoapp.presentation.ui.componants.SearchBar
class SearchScreen:Screen {
    @Composable
    override fun Content() {
        SearchBar()
        LazyColumn {}
    }
}