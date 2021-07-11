package com.pawels96.skyrimperkcalculator.presentation.viewmodels

sealed class AppEvent(val success: Boolean, val messageID: Int?) {
    class BuildSaved(success: Boolean, messageID: Int?) : AppEvent(success, messageID)
    class BuildRenamed(success: Boolean, messageID: Int?) : AppEvent(success, messageID)
    class BuildDeleted(success: Boolean, messageID: Int?) : AppEvent(success, messageID)
}