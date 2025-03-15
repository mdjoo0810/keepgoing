package io.github.mdjoo0810.keepgoing.infrastructure.db.jpa

import io.github.mdjoo0810.keepgoing.infrastructure.db.ContentEntity
import io.github.mdjoo0810.keepgoing.infrastructure.db.ContentListItemInterface
import org.springframework.data.jpa.repository.JpaRepository

internal interface ContentEntityJpaRepository : JpaRepository<ContentEntity, Long> {
    fun findAllByUserId(userId: Long): List<ContentListItemInterface>

    fun <T> findBySlug(
        slug: String,
        type: Class<T>,
    ): T
}
