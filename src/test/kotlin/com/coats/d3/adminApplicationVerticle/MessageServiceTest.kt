package com.coats.d3.adminApplicationVerticle

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import io.vertx.core.Vertx
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith

@Tag("unitTest")
@ExtendWith(VertxExtension::class)
class MessageServiceTest {

  /*@BeforeEach
  fun deploy_verticle(vertx: Vertx, testContext: VertxTestContext) {
    vertx.deployVerticle(MainVerticle(), testContext.succeeding<String> { _ -> testContext.completeNow() })
    verticle = MainVerticle()
  }

  @Test
  fun verticle_deployed(vertx: Vertx, testContext: VertxTestContext) {
    testContext.completeNow()
  }

  private lateinit var verticle: MainVerticle


  @Test
  @DisplayName("Should return the correct message")
  fun shouldReturnCorrectMessage() {
    val message = verticle.getMessage()
    Assertions.assertEquals(message,"Hello World!")
  }
*/
  @Test
  @DisplayName("Handler parsing")
  fun checkHandlerParsing() {
    val handlebars = Handlebars()
    val template = handlebars.compile("mytemplate")

    val templateString = template.apply("Satheesh")
    println("----->$templateString")
    Assertions.assertTrue("Satheesh" in templateString)
  }

  @Test
  @DisplayName("Handler parsing")
  fun checkHandlerParsingHtml() {
       val loader = ClassPathTemplateLoader("/templates", ".html")
       println("Hello World ${loader.prefix}   and ${loader.suffix}")
       val handlebars = Handlebars(loader)
       val template= handlebars.compile("myTemplate")
       val templateString = template.apply("Satheesh")
       println("----->$templateString")
    Assertions.assertTrue("Satheesh" in templateString)
  }
}
