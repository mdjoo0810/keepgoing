package io.github.mdjoo0810.keepgoing.domain.content

import java.time.LocalDateTime

data class ContentListItem(
    val slug: String,
    val title: String,
    val writer: ContentWriter,
    val lastModifiedAt: LocalDateTime,
)
