package com.eraga.adressbooktesttask.resource

import com.eraga.adressbooktesttask.dto.AddContactRequest
import com.eraga.adressbooktesttask.dto.ContactResponse
import com.eraga.adressbooktesttask.dto.UpdateContactRequest
import org.springframework.http.ResponseEntity


interface ContactResource {
    fun findById(id:Long): ResponseEntity<ContactResponse?>
    fun findAll(): ResponseEntity<List<ContactResponse>>
    fun save(addContactRequest: AddContactRequest): ResponseEntity<ContactResponse>
    fun update(id:Long,updatePersonRequest: UpdateContactRequest): ResponseEntity<ContactResponse>
    fun deleteById(id:Long) :ResponseEntity<ContactResponse>
    fun getReport() :ResponseEntity<MutableMap<String, MutableMap<String, MutableList<ContactResponse>>>>
}