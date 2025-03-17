package io.github.mdjoo0810.keepgoing.domain.content

import org.springframework.stereotype.Service

@Service
class ContentService(
    private val contentRepository: ContentRepository,
) {
    fun getContentsByWriter(name: String): List<ContentListItem> {
        return contentRepository.getContents(name)
    }
}
