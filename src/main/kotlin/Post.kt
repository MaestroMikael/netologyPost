package Post

data class Post(
    val id: Int?,
    val author: String?,
    val date: Long?,
    val text: String?,
    val likes: Likes = Likes(),
    val attachment: WallService.Attachment
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

    open class Attachment(val type: String)

    data class PhotoAttachment(val url: String, val description: String) : Attachment("photo")  // фото наследник

    data class AudioAttachment(val title: String, val artist: String) : Attachment("audio")  // аудио наследник

    data class VideoAttachment(val url: String, val duration: Int) : Attachment("video") //видео наследник
}

fun main() {

    val photoAttachment = WallService.PhotoAttachment("https://sun9-34.userapi.com/impg/lRpzBSt7gMvFPve0F35m9TWWPpnKaNfo7MEecw/gmAHHBUY_XQ.jpg?size=1439x2160&quality=95&sign=ad479ad94a59174119d0ce48397ff890&type=album", "MySon")
    val videoAttachment = WallService.VideoAttachment("https://vk.com/video-52671851_456250043", 180)

    val likes = Likes(99)
    WallService.add(Post(1, "Me", 2983, "hello post", likes, photoAttachment))
    WallService.updatePost(Post(1, "Me", 2955, "hello post", likes, videoAttachment))

    WallService.clearPostId(1)
    WallService.postPrint()

}
