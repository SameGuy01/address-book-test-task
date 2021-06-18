package com.eraga.adressbooktesttask.service

import com.eraga.adressbooktesttask.dao.ContactDao
import com.eraga.adressbooktesttask.domain.Contact
import com.eraga.adressbooktesttask.dto.AddContactRequest
import com.eraga.adressbooktesttask.dto.ContactResponse
import com.eraga.adressbooktesttask.dto.UpdateContactRequest
import com.eraga.adressbooktesttask.transform.AddContactTransform
import com.eraga.adressbooktesttask.transform.toContactResponse
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

    override fun update(id:Long,updatePersonRequest: UpdateContactRequest): ContactResponse {
        val contact = this.findContactById(id) ?: throw IllegalStateException("$id not found")

        return this.saveOrUpdate(contact.apply {
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

    override fun getReport(): MutableMap<String, MutableMap<String, MutableList<ContactResponse>>> {
        val list:List<ContactResponse> = findAll()
        val listMap: MutableMap<String, MutableMap<String, MutableList<ContactResponse>>> = LinkedHashMap()

        for (elem in list) {
            var localityMap: MutableMap<String, MutableList<ContactResponse>> = LinkedHashMap()
            var contactResponseList: MutableList<ContactResponse> = ArrayList()

            val region = elem.region
            val locality = elem.locality

            if (listMap.containsKey(region)) localityMap = listMap[region]!!
                if (localityMap.containsKey(locality)) contactResponseList = localityMap[locality]!!

            contactResponseList.add(elem)
            localityMap[locality] = contactResponseList
            listMap[region] = localityMap
        }
        return listMap
    }

    private fun findContactById(id:Long): Contact? = this.contactDao.findByIdOrNull(id)

    private fun saveOrUpdate(contact: Contact): ContactResponse = this.contactDao.save(contact).toContactResponse()
}