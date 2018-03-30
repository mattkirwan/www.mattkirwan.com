package com.mattkirwan

import org.scalatra.ScalatraServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.HandlerCollection
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

object Main extends App {
  val server = new Server(8080)
  val context = new WebAppContext()
  context.setContextPath("/")
  context.setResourceBase("src/main/webapp")
  context.addEventListener(new ScalatraListener)
  context.addServlet(classOf[DefaultServlet], "/")

  val handlers = new HandlerCollection
  handlers.addHandler(context)

  server.setHandler(handlers)

  server.start()
  server.join()
}

class Website extends ScalatraServlet {

  get("/") {
    views.html.home()
  }

  get("/post/:slug") {
    PostDao.posts find (_.slug == params("slug")) match {
      case Some(post) => views.html.post(post.title, post.summary, post.body)
      case None => halt(404, "Not found", Map("ContentType" -> "text/html"))
    }
  }

}
