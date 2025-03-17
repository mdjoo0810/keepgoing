package io.github.mdjoo0810.keepgoing.domain

import io.github.mdjoo0810.keepgoing.domain.content.Content
import io.github.mdjoo0810.keepgoing.domain.content.ContentRepository
import io.github.mdjoo0810.keepgoing.domain.content.ContentWriter
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.StopWatch

@SpringBootTest
class ContentRepositoryTest {
    @Autowired
    private lateinit var contentRepository: ContentRepository

    @BeforeEach
    fun setUp() {
        val sb = StringBuilder()
        repeat(100000) {
            sb.append("$it")
        }
        val dummyContent = sb.toString()
        repeat(100000) {
            val content =
                Content(
                    slug = "slug-$it",
                    title = "title-$it",
                    content = dummyContent,
                    writer = ContentWriter("test-writer"),
                )
            contentRepository.insert(content)
        }
    }

    @AfterEach
    fun tearDown() {
        contentRepository.deleteAll()
    }

    @Test
    fun `컨텐츠 전체 가져오기`() {
        val sw = StopWatch()
        sw.start()
        val contents = contentRepository.getContents("test-writer")
        sw.stop()
        println("게시물 수 ${contents.size} 총 걸린 시간(ms): ${sw.totalTimeMillis}")
    }
}
