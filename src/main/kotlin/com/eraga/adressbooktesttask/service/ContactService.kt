package com.eraga.adressbooktesttask.service

import com.eraga.adressbooktesttask.dto.AddContactRequest
import com.eraga.adressbooktesttask.dto.ContactResponse
import com.eraga.adressbooktesttask.dto.UpdateContactRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface ContactService {
    fun findById(id:Long): ContactResponse?
    fun findAll(): List<ContactResponse>
    fun save(addPersonRequest: AddContactRequest): ContactResponse
    fun update(id:Long,updatePersonRequest: UpdateContactRequest): ContactResponse
    fun deleteById(id:Long)
}