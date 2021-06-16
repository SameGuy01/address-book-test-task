package com.eraga.adressbooktesttask.service

import com.eraga.adressbooktesttask.transform.toContactResponse
import com.eraga.adressbooktesttask.dao.ContactDao
import com.eraga.adressbooktesttask.domain.Contact
import com.eraga.adressbooktesttask.dto.AddContactRequest
import com.eraga.adressbooktesttask.dto.ContactResponse
import com.eraga.adressbooktesttask.dto.UpdateContactRequest
import com.eraga.adressbooktesttask.transform.AddContactTransform
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ContactServiceImpl(private val contactDao: ContactDao,
                         private val addContactTransform: AddContactTransform) : ContactService {
    override fun findById(id: Long): ContactResponse? = this.findContactById(id).toContactResponse()

    override fun findAll(): List<ContactResponse> {
        return this.contactDao.findAll().map(Contact::toContactResponse)
    }

    override fun save(addContactRequest: AddContactRequest): ContactResponse {
        return this.saveOrUpdate(addContactTransform.transform(addContactRequest))
    }

    override fun update(updatePersonRequest: UpdateContactRequest): ContactResponse {
        val contact = this.findContactById(updatePersonRequest.id) ?: throw IllegalStateException("${updatePersonRequest.id} not found")

        return this.saveOrUpdate(contact.apply {
            this.comment = updatePersonRequest.comment
            this.email = updatePersonRequest.email
            this.locality = updatePersonRequest.locality
            this.name = updatePersonRequest.name
            this.phone = updatePersonRequest.phone
            this.region = updatePersonRequest.region
            this.photo = updatePersonRequest.photo
        })
    }

    override fun deleteById(id: Long) {
        this.contactDao.deleteById(id)
    }

    private fun findContactById(id:Long): Contact? = this.contactDao.findByIdOrNull(id)

    private fun saveOrUpdate(contact: Contact): ContactResponse = this.contactDao.save(contact).toContactResponse()
}