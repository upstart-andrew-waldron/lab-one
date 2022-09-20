package com.upstart.learning.introduction

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LearningIntroductionApplication

/*
 * Kata/Problem: Moving & Box Packing
 *
 * Start: Create inventory of boxes + contents
 *   Box: Numerical identifier, size, label (string)
 *   Contents: List of strings
 *
 * Configuration:
 *   Database connection
 *   Max number items per box
 *
 * Advanced problem (if time): We want to help our customers out in packing their truck! Our customers
 * want a service that operates as follows:
 *   1. Given the inventory from the previous item, allow dimensions to be added to each box/item
 *   2. Add a truck with dimensions.
 *   3. Answer the following questions: Can all the items fit? What item(s) won't fit? (maximize for large items fitting)
 *   4. Suggest the mechanism for how to pack the truck to fit all the items?
 */

fun main(args: Array<String>) {
    runApplication<LearningIntroductionApplication>(*args)
}
