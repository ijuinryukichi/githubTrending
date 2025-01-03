package com.example.githubtrending.ui.feature.explore

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.githubtrending.ui.transition.webViewScreenRouteParameterName
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val url = savedStateHandle.get<String>(webViewScreenRouteParameterName)

}