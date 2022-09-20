package com.upstart.learning.introduction.controller

import com.upstart.learning.introduction.model.dto.BoxCreateRequest
import com.upstart.learning.introduction.model.dto.BoxCreateResponse
import com.upstart.learning.introduction.service.BoxService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/box/")
class BoxController(
    var boxService: BoxService
) {
    @PostMapping
    fun post(@RequestBody boxCreateRequest: BoxCreateRequest) : Mono<ResponseEntity<BoxCreateResponse>> {
        return Mono.empty()

//        boxService.loadBoxFromRequest()
//        return Mono.just(ResponseEntity.ok())
    }
}