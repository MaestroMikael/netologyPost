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
        PhotoAttachment(Photo(1,1,"Me",215141)),
        AudioAttachment(Audio(1,1,"My_song",2545,151541)),
        VideoAttachment(Video(1,1,"MY_day",2654568))
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