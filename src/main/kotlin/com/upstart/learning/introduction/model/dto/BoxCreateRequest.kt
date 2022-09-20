package com.upstart.learning.introduction.model.dto

data class BoxCreateRequest(
    val label : String,
    val width : String,
    val height : String,
    val depth : String,
    val weight : String
)


