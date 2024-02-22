package Post

data class Post(
    val id: Int,
    val autor: String,
    val data: Long,
    val text: String,
    val likes: Likes = Likes()
)
data class Likes(var count:Int = 0)

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
        for ((index, post) in WallService.posts.withIndex()) {
            if (post.id == newPost.id) {
                WallService.posts[index] = newPost.copy(likes = post.likes.copy())
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

fun main() {
    val likes = Likes(100)
    WallService.add(Post(1, "Me", 2983, "hello post",likes))
    WallService.updatePost(Post(1, "Me", 2483, "hello post 2",likes))
    WallService.clearPostId(1)
    WallService.postPrint()
}
