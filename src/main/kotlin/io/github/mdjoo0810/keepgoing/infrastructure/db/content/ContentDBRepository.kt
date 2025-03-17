package io.github.mdjoo0810.keepgoing.infrastructure.db.content

import io.github.mdjoo0810.keepgoing.domain.content.Content
import io.github.mdjoo0810.keepgoing.domain.content.ContentListItem
import io.github.mdjoo0810.keepgoing.domain.content.ContentRepository
import io.github.mdjoo0810.keepgoing.infrastructure.db.content.jpa.ContentEntityJpaRepository
import io.github.mdjoo0810.keepgoing.infrastructure.db.content.jpa.UserEntityJpaRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository

@Repository
class ContentDBRepository internal constructor(
    private val contentEntityJpaRepository: ContentEntityJpaRepository,
    private val userEntityJpaRepository: UserEntityJpaRepository,
) : ContentRepository {
    @Transactional
    override fun insert(content: Content): Content {
        var user = userEntityJpaRepository.findByName(content.writer.name)
        if (user == null) {
            user = userEntityJpaRepository.save(content.writer.toUserEntity())
        }

        val contentEntity = contentEntityJpaRepository.save(content.toContentEntity(user.id))
        return contentEntity.toContent(user.toContentWriter())
    }

    override fun getContents(writerName: String): List<ContentListItem> {
        val user =
            userEntityJpaRepository.findByName(writerName)
                ?: throw IllegalArgumentException("작성자가 없습니다")
        val contents = contentEntityJpaRepository.findAllByUserId(user.id)

        return contents.map { it.toContentListItem(user.toContentWriter()) }
    }

    @Transactional
    override fun deleteAll() {
        contentEntityJpaRepository.deleteAll()
        userEntityJpaRepository.deleteAll()
    }
}
