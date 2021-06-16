package com.eraga.adressbooktesttask.resource

import com.eraga.adressbooktesttask.dto.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class ErrorHandlerResource {

    @ExceptionHandler(java.lang.IllegalStateException::class)
    fun handleIllegalState(ex: IllegalStateException) :ResponseEntity<ErrorResponse>{
        return ResponseEntity.badRequest().body(ErrorResponse(message = ex.localizedMessage))
    }
}