package io.github.mdjoo0810.keepgoing.infrastructure.db

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
internal class ContentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val userId: Long,
    val slug: String,
    var title: String,
    @Column(columnDefinition = "TEXT")
    var content: String,
) : BaseEntity()
