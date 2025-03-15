package io.github.mdjoo0810.keepgoing.infrastructure.db

import java.time.LocalDateTime

interface ContentListItemInterface {
    val slug: String
    val title: String
    val updatedAt: LocalDateTime
}
