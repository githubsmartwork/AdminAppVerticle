package com.coats.d3.adminApplicationVerticle

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.Handler
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import java.net.InetAddress


class MainVerticle : AbstractVerticle() {

  override fun start(startPromise: Promise<Void>) {
    val router = createRouter()
    vertx
      .createHttpServer()
      .requestHandler(router)
      .listen(8080) { http ->
        if (http.succeeded()) {
          startPromise.complete()
          println("HTTP server started on port 8080")
        } else {
          startPromise.fail(http.cause());
        }
      }
  }

  fun getMessage(routingContext: RoutingContext): String? {
    var templateString: String? =null
    try {
      /*val handlebars = Handlebars()
      val template = handlebars.compile("mytemplate")
      println("$template.filename()")
      val templateString = template.apply("Satheesh")
      println("The result is ---->"+templateString)*/

      val loader = ClassPathTemplateLoader("/templates", ".html")
      val handlebars = Handlebars(loader)
      val template= handlebars.compile("myTemplate")
      val templateValue=getHostAddress(routingContext)
      templateString = template.apply(templateValue)
     // println("----->$templateString")
    }
    catch(e:Exception){
      println("Error message is ---->${e.message}")
      println("something went wrong")
    }
    return templateString
  }

  private fun createRouter() = Router.router(vertx).apply {
    route().handler(BodyHandler.create())
    get("/").handler(handlerRoot)
    }

  private val handlerRoot = Handler<RoutingContext> { routingContext ->
    val response = routingContext.response()
       response.putHeader("content-type", "text/html")
      .setChunked(true)
      .write(getMessage(routingContext))
      .end()
  }

  open fun getHostAddress(routingContext: RoutingContext): String? {
    val ipAddress= routingContext.request().host()
    //val ipAddress = routingContext.request().remoteAddress().host()
    //println("headers---->"+routingContext.request().headers()
   return ipAddress;
  }
}

