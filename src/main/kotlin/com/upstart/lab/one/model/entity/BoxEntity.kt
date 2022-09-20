package com.upstart.lab.one.model.entity

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("box")
data class BoxEntity(
    @Column("id")
    val boxId : Long? = null,

    @Column("box_label")
    val label: String,
    val width: String,
    val height: String,
    val depth: String,
    val weight: String,
)
