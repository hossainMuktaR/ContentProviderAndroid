package com.example.feature.note.utils

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
