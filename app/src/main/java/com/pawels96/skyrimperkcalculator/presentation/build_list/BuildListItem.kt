package com.pawels96.skyrimperkcalculator.presentation.build_list

import com.pawels96.skyrimperkcalculator.domain.Build

data class BuildListItem(
    val build: Build,
    val isSelected: Boolean,
    val level: Int
)