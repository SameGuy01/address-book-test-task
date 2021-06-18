package com.eraga.adressbooktesttask.resource

import com.eraga.adressbooktesttask.dto.AddContactRequest
import com.eraga.adressbooktesttask.dto.ContactResponse
import com.eraga.adressbooktesttask.dto.UpdateContactRequest
import com.eraga.adressbooktesttask.service.ContactServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.net.URI


@Controller
@RequestMapping("/api/v1/contacts")
class ContactResourceImpl(private val contactService: ContactServiceImpl) : ContactResource {

    @GetMapping("/{id}")
    override fun findById(@PathVariable id: Long): ResponseEntity<ContactResponse?> {
        val contactResponse: ContactResponse? = this.contactService.findById(id)
        return ResponseEntity.ok().body(contactResponse)
    }

    @GetMapping
    override fun findAll(): ResponseEntity<List<ContactResponse>> {
        return ResponseEntity.ok(this.contactService.findAll())
    }

    @PostMapping
    override fun save(@RequestBody addContactRequest: AddContactRequest): ResponseEntity<ContactResponse> {
        val contactResponse = this.contactService.save(addContactRequest)
        return ResponseEntity
            .created(URI.create("/api/v1/contacts".plus("/${contactResponse.id}")))
            .body(contactResponse)
    }

    @PutMapping("/{id}")
    override fun update(@PathVariable id: Long, @RequestBody updatePersonRequest: UpdateContactRequest): ResponseEntity<ContactResponse> {
        return ResponseEntity.ok(this.contactService.update(id, updatePersonRequest))
    }

    @DeleteMapping("/{id}")
    override fun deleteById(@PathVariable id: Long): ResponseEntity<ContactResponse> {
        this.contactService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/report")
    override fun getReport(): ResponseEntity<MutableMap<String, MutableMap<String, MutableList<ContactResponse>>>> {
        return ResponseEntity.ok(this.contactService.getReport())
    }
}