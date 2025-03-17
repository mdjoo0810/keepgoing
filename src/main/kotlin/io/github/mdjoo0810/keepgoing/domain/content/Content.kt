package io.github.mdjoo0810.keepgoing.domain.content

import java.time.LocalDateTime

data class Content(
    val slug: String,
    val title: String,
    val content: String,
    val writer: ContentWriter,
    val lastModifiedAt: LocalDateTime = LocalDateTime.now(),
)

data class ContentWriter(
    val name: String,
)
