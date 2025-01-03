package com.example.githubtrending.ui.stable

import androidx.compose.runtime.Stable

@Stable
class StableSendViewableLogInvoker(
    private val invoker: (Int) -> Unit
) {

    operator fun invoke(index: Int) {
        invoker(index)
    }
}