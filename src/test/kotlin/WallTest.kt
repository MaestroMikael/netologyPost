import Post.*
import org.junit.Assert.*
import org.junit.*

class WallServiceTest {

    @Before
    fun setUp() {  // Инициализируем настройки теста
        WallService.posts = emptyArray()
        WallService.lastId = 0
    }

    @Test
    fun testAddPost() {   // Тест на дол=бавление поста
        val post = Post(1, "Me", 2983, "hello post", Likes(100))
        val addedPost = WallService.add(post)
        assertEquals(1, addedPost.id)
    }

    @Test
    fun testUpdatePost() {  // Тест на обновление
        val initialPost = Post(1, "Me", 2983, "hello post", Likes(100))
        WallService.add(initialPost)

        val updatedPost = Post(1, "Me", 2483, "hello post 2", Likes(100))
        val isUpdated = WallService.updatePost(updatedPost)
        assertTrue(isUpdated)

        val retrievedPost = WallService.posts.find { it.id == 1 }
        assertEquals(updatedPost, retrievedPost)
    }

    @Test
    fun testClearPostId() {
        val post = Post(1, "Me", 2983, "hello post", Likes(100))
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