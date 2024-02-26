package Post

data class Post(
    val id: Int?,
    val author: String?,
    val date: Long?,
    val text: String?,
    val likes: Likes = Likes(),
    val attachments: List<Attachment>
)

data class Likes(var count: Int = 0)

object WallService {

    var posts = emptyArray<Post>()
    var lastId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId, likes = post.likes.copy())
        return posts.last()
    }

    fun postPrint() {
        for (post in posts)
            print(post)
        print(" ")
        println()
    }

    fun updatePost(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                posts[index] = newPost.copy(likes = post.likes.copy())
                return true
            }
        }
        return false
    }

    fun clearPostId(postId: Int): Boolean {
        val postIndex = posts.indexOfFirst { it.id == postId }
        if (postIndex != -1) {
            val post = posts[postIndex]
            posts[postIndex] = post.copy(id = 0)
            return true
        }
        return false
    }

}

open class Attachment(val type: String)

data class PhotoAttachment(val url: String, val description: String) : Attachment("photo")  // фото наследник

data class AudioAttachment(val title: String, val artist: String) : Attachment("audio")  // аудио наследник

data class VideoAttachment(val url: String, val duration: Int) : Attachment("video") //видео наследник

data class DocumentAttachment(val url: String, val author: String) : Attachment("document")

data class LinktAttachment(val url: String, val title: String) : Attachment("link")


fun main() {

    val attachments = listOf(
        PhotoAttachment(
            "https://sun9-67.userapi.com/impg/P1fDGDk2DuW9wtva2Mg0yFO5O44fdIjrjRTFWw/EC2UsluBYQQ.jpg?size=853x1280&quality=95&sign=ae54302b4ccc9793cf5b85adde759a33&type=album",
            "Mark"
        ),
        AudioAttachment("Название аудио", "Исполнитель"),
        VideoAttachment("http://example.com/video1", 120)
    )
    val likes = Likes(99)
    WallService.add(Post(1, "Me", 2983, "hello post", likes, attachments))

    WallService.clearPostId(1)
    WallService.postPrint()

}