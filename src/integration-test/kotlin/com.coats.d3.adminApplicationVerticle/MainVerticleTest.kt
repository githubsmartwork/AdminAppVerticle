package com.coats.d3.adminApplicationVerticle

import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.ext.web.RoutingContext
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith

@Tag("integrationTest")
@ExtendWith(VertxExtension::class)
class MainVerticleTest {



  @BeforeEach
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
    val handlerRoot = Handler<RoutingContext> { routingContext ->
      val message = verticle.getMessage(routingContext)
      Assertions.assertEquals(message, "Hello World!")
    }
  }
}
