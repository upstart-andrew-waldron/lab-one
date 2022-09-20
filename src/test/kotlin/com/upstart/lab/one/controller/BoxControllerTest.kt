package com.upstart.lab.one.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.upstart.lab.one.model.dto.BoxCreateRequest
import com.upstart.lab.one.model.dto.BoxCreateResponse
import com.upstart.lab.one.service.BoxService
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.nio.file.Files
import java.util.function.Consumer

@WebFluxTest(controllers = [BoxController::class])
internal class BoxControllerTest {
    @Autowired
    lateinit var webClient: WebTestClient

    @Value("classpath:/box/post-response.json")
    lateinit var boxPostResponseResource: Resource
    private lateinit var boxPostResponseJsonString: String

    @Value("classpath:/box/post-request.json")
    lateinit var boxPostRequestResource: Resource
    private lateinit var boxPostRequest: BoxCreateRequest

    @MockkBean
    lateinit var boxService: BoxService

    private var mapper: ObjectMapper = ObjectMapper().findAndRegisterModules()

    @BeforeEach
    internal fun setUp() {
        boxPostRequest = loadFromResource(boxPostRequestResource, object : TypeReference<BoxCreateRequest>() {})
        boxPostResponseJsonString = loadResourceAsString(boxPostResponseResource)

        every {
            boxService.loadBoxFromRequest(any(), any())
        } answers {
            secondArg< Consumer<BoxCreateResponse>>().accept(
                BoxCreateResponse(
                1234
            )
            );
        }
    }

    @Test
    internal fun `given a valid box request when post is called then a box is created`() {
        webClient.post()
            .uri("/box/")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(boxPostRequest))
            .exchange()
            .expectStatus().isOk
            .expectBody().json(boxPostResponseJsonString)
    }

    private fun <T> loadFromResource(resource: Resource, typeReference: TypeReference<T>): T {
        val resourceAsString = Files.readString(resource.file.toPath())
        return mapper.readValue(resourceAsString, typeReference)
    }

    private fun loadResourceAsString(resource: Resource): String {
        return Files.readString(resource.file.toPath())
    }
}