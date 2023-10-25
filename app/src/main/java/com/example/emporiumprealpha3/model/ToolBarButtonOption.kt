package com.example.emporiumprealpha3.model;

import com.example.emporiumprealpha3.R
import org.jetbrains.annotations.Nullable

enum class ToolBarButtonOption(
    val contentDescription: String,
    val iconResource: Int?
) {
    MENU(
        "Navigation Menu", R.drawable.menu_24px
    ),
    FILTER(
        "Filter Menu", R.drawable.filter_24px
    ),
    NONE(
        "", null
    )
}
