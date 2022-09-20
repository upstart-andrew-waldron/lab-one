package com.upstart.lab.one.controller

import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest

@WebFluxTest(controllers = [BoxController::class])
internal class BoxControllerTest {

}