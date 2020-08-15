package com.pawels96.skyrimperkcalculator.presentation.viewmodels

open class LiveEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun getContent(): T = content
}

class EventWithID<out T, out I>(data: T, private val id: I) : LiveEvent<T>(data) {
    fun getID() : I = id
}