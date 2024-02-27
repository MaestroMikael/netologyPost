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

data class PhotoAttachment(val photo: Photo) : Attachment("photo")  // фото наследник
data class Photo(val id: Int, val ownerId: Int, val text: String, val date: Long)

data class AudioAttachment(val audio: Audio) : Attachment("audio")  // аудио наследник
data class Audio(val id: Int, val ownerId: Int, val title: String, val duration: Int, val date: Long)

data class VideoAttachment(val video : Video) : Attachment("video") //видео наследник
data class Video(val id: Int, val ownerId: Int, val title: String, val duration: Int)

data class DocumentAttachment(val document: Document) : Attachment("document")
data class Document(val id: Int, val ownerId: Int, val title: String, val size: Int ,val url: String)

data class LinkAttachment(val link: Link) : Attachment("link")
data class Link(val url: String,val title: String,val caption: String)


fun main() {

    val attachments = listOf(
        PhotoAttachment(Photo(1,1,"Me",215141)),
        AudioAttachment(Audio(1,1,"My_song",2545,151541)),
        VideoAttachment(Video(1,1,"MY_day",2654568))
    )
    val likes = Likes(99)
    WallService.add(Post(1, "Me", 2983, "hello post", likes, attachments))

    WallService.clearPostId(1)
    WallService.postPrint()

}