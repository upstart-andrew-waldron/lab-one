package com.upstart.lab.one.service

import com.upstart.lab.one.model.dto.BoxCreateRequest
import com.upstart.lab.one.model.dto.BoxCreateResponse
import com.upstart.lab.one.model.entity.BoxEntity
import com.upstart.lab.one.repository.BoxRepository
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class BoxService(
    val boxRepository: BoxRepository
) {
    fun createNewBox(boxCreateRequest: BoxCreateRequest, response: Consumer<BoxCreateResponse>) {
        boxRepository
            .save(createEntityFromRequest(boxCreateRequest))
            .subscribe {
                response.accept(BoxCreateResponse(it.boxId!!))
            }
    }

    private fun createEntityFromRequest(boxCreateRequest: BoxCreateRequest): BoxEntity {
        return BoxEntity(
            label = boxCreateRequest.label,
            width = boxCreateRequest.width,
            height = boxCreateRequest.height,
            depth = boxCreateRequest.depth,
            weight = boxCreateRequest.weight
        )
    }
}