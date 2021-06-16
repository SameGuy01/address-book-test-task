package com.eraga.adressbooktesttask.dto

import java.time.LocalDateTime

data class ErrorResponse (
    val title:String="BadRequest",
    val message:String,
    val dateTime:LocalDateTime = LocalDateTime.now())