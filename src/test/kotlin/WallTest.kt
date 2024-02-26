import Post.*
import org.junit.Assert.*
import org.junit.*

class WallServiceTest {

    @Before
    fun setUp() {  // Инициализируем настройки теста
        WallService.posts = emptyArray()
        WallService.lastId = 0
    }

    val attachments = listOf(
        PhotoAttachment(
            "https://sun9-67.userapi.com/impg/P1fDGDk2DuW9wtva2Mg0yFO5O44fdIjrjRTFWw/EC2UsluBYQQ.jpg?size=853x1280&quality=95&sign=ae54302b4ccc9793cf5b85adde759a33&type=album",
            "Mark"
        ),
        AudioAttachment("Название аудио", "Исполнитель"),
        VideoAttachment("http://example.com/video1", 120)
    )
    @Test
    fun testAddPost() { // Тест на дол=бавление поста

        val post = Post(1, "Me", 2983, "hello post", Likes(100),attachments)
        val addedPost = WallService.add(post)
        assertEquals(1, addedPost.id)
    }

    @Test
    fun testUpdatePost() {  // Тест на обновление
        val initialPost = Post(1, "Me", 2983, "hello post", Likes(100),attachments)
        WallService.add(initialPost)

        val updatedPost = Post(1, "Me", 2483, "hello post 2", Likes(100),attachments)
        val isUpdated = WallService.updatePost(updatedPost)
        assertTrue(isUpdated)

        val retrievedPost = WallService.posts.find { it.id == 1 }
        assertEquals(updatedPost, retrievedPost)
    }

    @Test
    fun testClearPostId() {
        val post = Post(1, "Me", 2983, "hello post", Likes(100),attachments)
        WallService.add(post)

        val isCleared = WallService.clearPostId(1)
        assertTrue(isCleared)

        val retrievedPost = WallService.posts.find { it.id == 1 }
        assertEquals(null, retrievedPost?.id)
    }

    @Test
    fun testClearPostIdNonExistent() {
        val isCleared = WallService.clearPostId(1)
        assertFalse(isCleared)
    }
}