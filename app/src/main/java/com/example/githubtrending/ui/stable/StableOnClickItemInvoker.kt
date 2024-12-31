package com.example.githubtrending.ui.stable

import androidx.compose.runtime.Stable

@Stable
class StableOnClickItemInvoker(
    private val invoker: () -> Unit
) {

    fun invoke() {
        invoker()
    }
}