package io.github.mdjoo0810.keepgoing.domain.content

interface ContentRepository {
    fun insert(content: Content): Content

    fun getContents(writerName: String): List<ContentListItem>

    fun deleteAll()
}
