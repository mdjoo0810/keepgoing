package io.github.mdjoo0810.keepgoing.infrastructure.db.content.jpa

import io.github.mdjoo0810.keepgoing.infrastructure.db.content.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface UserEntityJpaRepository : JpaRepository<UserEntity, Long> {
    fun findByName(name: String): UserEntity?
}
