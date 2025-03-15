package io.github.mdjoo0810.keepgoing.infrastructure.db

import java.time.LocalDateTime

data class ContentListItemDto(
    val slug: String,
    var title: String,
    var updatedAt: LocalDateTime,
)
