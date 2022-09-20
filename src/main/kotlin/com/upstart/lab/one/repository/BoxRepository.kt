package com.upstart.lab.one.repository

import com.upstart.lab.one.model.entity.BoxEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface BoxRepository : ReactiveCrudRepository<BoxEntity, Long>