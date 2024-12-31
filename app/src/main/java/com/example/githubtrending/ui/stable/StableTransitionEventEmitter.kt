package com.example.githubtrending.ui.stable

import androidx.compose.runtime.Stable
import com.example.githubtrending.ui.transition.TransitionEventInterface

@Stable
class StableTransitionEventEmitter(private val sendFunc: (TransitionEventInterface) -> Unit) {

    fun trySend(event: TransitionEventInterface) {
        sendFunc(event)
    }

    suspend fun send(event: TransitionEventInterface) {
        sendFunc(event)
    }
}