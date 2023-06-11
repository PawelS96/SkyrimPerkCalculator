package com.pawels96.skyrimperkcalculator.presentation.build_list

sealed class BuildListEvent(val success: Boolean, val messageID: Int?) {
    class BuildSaved(success: Boolean, messageID: Int?) : BuildListEvent(success, messageID)
    class BuildRenamed(success: Boolean, messageID: Int?) : BuildListEvent(success, messageID)
    class BuildDeleted(success: Boolean, messageID: Int?) : BuildListEvent(success, messageID)
}