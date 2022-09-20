package com.upstart.learning.introduction.service

import com.upstart.learning.introduction.model.dto.BoxCreateRequest
import com.upstart.learning.introduction.model.dto.BoxCreateResponse
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Service
class BoxService {
    fun loadBoxFromRequest(boxRequest: BoxCreateRequest, consumer: Consumer<BoxCreateResponse>) {

    }
}