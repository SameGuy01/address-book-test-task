package com.eraga.adressbooktesttask.service

import com.eraga.adressbooktesttask.dto.AddContactRequest
import com.eraga.adressbooktesttask.dto.ContactResponse
import com.eraga.adressbooktesttask.dto.UpdateContactRequest


interface ContactService {
    fun findById(id:Long): ContactResponse?
    fun findAll(): List<ContactResponse>
    fun save(addPersonRequest: AddContactRequest): ContactResponse
    fun update(id:Long,updatePersonRequest: UpdateContactRequest): ContactResponse
    fun deleteById(id:Long)
    fun getReport() :  MutableMap<String, MutableMap<String, MutableList<ContactResponse>>>
}