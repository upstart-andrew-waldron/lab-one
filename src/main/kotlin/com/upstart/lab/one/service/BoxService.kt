package com.upstart.lab.one.service

import com.upstart.lab.one.model.dto.BoxCreateRequest
import com.upstart.lab.one.model.dto.BoxCreateResponse
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class BoxService {
    fun createNewBox(boxCreateRequest: BoxCreateRequest, response: Consumer<BoxCreateResponse>) {

    }
}