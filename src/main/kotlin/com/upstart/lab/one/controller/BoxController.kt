package com.upstart.lab.one.controller

import com.upstart.lab.one.model.dto.BoxCreateRequest
import com.upstart.lab.one.model.dto.BoxCreateResponse
import com.upstart.lab.one.service.BoxService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.MonoSink

@RestController
@RequestMapping("/box/")
class BoxController(
    var boxService: BoxService
) {
    @PostMapping
    fun post(@RequestBody boxCreateRequest: BoxCreateRequest): Mono<ResponseEntity<BoxCreateResponse>> {
        return Mono.create { monoSink: MonoSink<ResponseEntity<BoxCreateResponse>> ->
            boxService.createNewBox(boxCreateRequest) {
                monoSink.success(ResponseEntity.ok(it))
            }
        }
    }
}