package com.example.core.redux

interface Reducer<A: Action, S: State> {

    fun reduce(
        action: A,
        currentState: S,
    ): S
}