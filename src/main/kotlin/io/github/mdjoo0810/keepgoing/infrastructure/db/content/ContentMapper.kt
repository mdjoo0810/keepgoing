package io.github.mdjoo0810.keepgoing.infrastructure.db.content

import io.github.mdjoo0810.keepgoing.domain.content.Content
import io.github.mdjoo0810.keepgoing.domain.content.ContentListItem
import io.github.mdjoo0810.keepgoing.domain.content.ContentWriter

internal fun Content.toContentEntity(userId: Long) =
    ContentEntity(
        slug = this.slug,
        title = this.title,
        content = this.content,
        userId = userId,
    )

internal fun ContentEntity.toContent(writer: ContentWriter) =
    Content(
        slug = this.slug,
        title = this.title,
        content = this.content,
        writer = writer,
        lastModifiedAt = this.updatedAt,
    )

internal fun ContentEntity.toContentListItem(writer: ContentWriter) =
    ContentListItem(
        slug = this.slug,
        title = this.title,
        writer = writer,
        lastModifiedAt = this.updatedAt,
    )

internal fun ContentListItemDto.toContentListItem(writer: ContentWriter) =
    ContentListItem(
        slug = this.slug,
        title = this.title,
        writer = writer,
        lastModifiedAt = this.updatedAt,
    )

internal fun ContentListItemInterface.toContentListItem(writer: ContentWriter) =
    ContentListItem(
        slug = this.slug,
        title = this.title,
        writer = writer,
        lastModifiedAt = this.updatedAt,
    )

internal fun ContentWriter.toUserEntity() = UserEntity(name = this.name)

internal fun UserEntity.toContentWriter() = ContentWriter(name = this.name)
