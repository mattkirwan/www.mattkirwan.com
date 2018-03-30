package com.mattkirwan.app

import org.scalatra.test.scalatest._

class MattKirwanWebsiteServletTests extends ScalatraFunSuite {

  addServlet(classOf[MattKirwanWebsiteServlet], "/*")

  test("GET / on MattKirwanWebsiteServlet should return status 200"){
    get("/"){
      status should equal (200)
    }
  }

}
