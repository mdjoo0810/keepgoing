package io.github.mdjoo0810.keepgoing.infrastructure.db.content

import io.github.mdjoo0810.keepgoing.infrastructure.db.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
internal class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val name: String,
) : BaseEntity()
