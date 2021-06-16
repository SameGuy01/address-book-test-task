package com.eraga.adressbooktesttask.resource

import com.eraga.adressbooktesttask.domain.Contact
import com.eraga.adressbooktesttask.dto.AddContactRequest
import com.eraga.adressbooktesttask.dto.ContactResponse
import com.eraga.adressbooktesttask.dto.UpdateContactRequest
import com.eraga.adressbooktesttask.service.ContactServiceImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.net.URI


@Controller
@RequestMapping("/api/v1/contacts")
class ContactResourceImpl(private val contactService: ContactServiceImpl) : ContactResource {

    @GetMapping("{id}")
    fun showContactById(model:Model, @PathVariable id:Long):String{
        val obj = findById(id).body
        model.addAttribute("contact",obj)
        return "edit"
    }

    override fun findById(id: Long): ResponseEntity<ContactResponse?> {
        val contactResponse: ContactResponse? = this.contactService.findById(id)
        return ResponseEntity.ok().body(contactResponse)
    }

    @GetMapping
    fun showAll(model: Model):String{
        val obj = findAll().body
        model.addAttribute("contacts",obj)
        return "main"
    }

    override fun findAll(): ResponseEntity<List<ContactResponse>> {
        return ResponseEntity.ok(this.contactService.findAll())
    }

    @PostMapping("/save")
    fun saveContact(@ModelAttribute("contact") addContactRequest: AddContactRequest):String{
        save(addContactRequest)
        return "redirect:/api/v1/contacts"
    }

    @GetMapping("/save")
    fun getSavePage(model: Model):String{
        model.addAttribute("contact", Contact());
        return "add-contact"
    }

    override fun save(@RequestBody addContactRequest: AddContactRequest): ResponseEntity<ContactResponse> {
        val contactResponse = this.contactService.save(addContactRequest)
        return ResponseEntity
                .created(URI.create("/api/v1/contacts".plus("/${contactResponse.id}")))
                .body(contactResponse)
    }

    @PostMapping("/edit/{id}")
    fun updateContact(@PathVariable id:Long,contactRequest: UpdateContactRequest):String{
        update(id,contactRequest)
        return "redirect:/api/v1/contacts"
    }

    override fun update(id:Long,updatePersonRequest: UpdateContactRequest): ResponseEntity<ContactResponse> {
        return ResponseEntity.ok(this.contactService.update(id,updatePersonRequest))
    }

    @GetMapping("/delete/{id}")
    fun deleteContactById(@PathVariable id: Long):String{
        deleteById(id)
        return "redirect:/api/v1/contacts"
    }

    override fun deleteById( id: Long) : ResponseEntity<ContactResponse>{
        this.contactService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}