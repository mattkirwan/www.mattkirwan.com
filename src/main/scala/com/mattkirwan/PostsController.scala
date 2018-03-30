package com.mattkirwan

case class Post(slug: String, title:String, summary:String, body:String)

object PostDao {
    val post1 = Post("post-one", "Post One", """The summary for post number one...""", """The main content for post one""")
    val post2 = Post("post-two", "Post Two", """The summary for post number two...""", """The main content for post two""")
    val posts = List(post1, post2)
}
