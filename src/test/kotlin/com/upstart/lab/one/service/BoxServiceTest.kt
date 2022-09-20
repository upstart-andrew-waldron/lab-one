package com.upstart.lab.one.service

import com.upstart.lab.one.model.dto.BoxCreateRequest
import com.upstart.lab.one.model.dto.BoxCreateResponse
import com.upstart.lab.one.model.entity.BoxEntity
import com.upstart.lab.one.repository.BoxRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import reactor.core.publisher.Mono

@SpringJUnitConfig
internal class BoxServiceTest {
    lateinit var subject: BoxService

    @MockK
    lateinit var boxRepository: BoxRepository

    @BeforeEach
    internal fun setUp() {
        every {
            boxRepository.save(any())
        } returns Mono.just(getBoxEntity())

        subject = BoxService(boxRepository)
    }

    @Test
    internal fun `when create is called it requests a save for the new box entity`() {
        val resultList : MutableList<BoxCreateResponse> = mutableListOf()

        subject.createNewBox(getBoxCreateRequest(), resultList::add)

        assertThat(resultList[0].boxId).isEqualTo(12345)
    }

    private fun getBoxCreateRequest(): BoxCreateRequest {
        return BoxCreateRequest(
            label = "request label",
            width = "request width",
            height = "request height",
            depth = "request depth",
            weight = "request weight"
        )
    }

    private fun getBoxEntity(): BoxEntity {
        return BoxEntity(
            boxId = 12345,
            label = "label",
            width = "width",
            height = "height",
            depth = "depth",
            weight = "weight"
        )
    }
}