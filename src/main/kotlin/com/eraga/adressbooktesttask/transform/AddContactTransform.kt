package com.eraga.adressbooktesttask.transform

import com.eraga.adressbooktesttask.domain.Contact
import com.eraga.adressbooktesttask.dto.AddContactRequest
import org.springframework.stereotype.Component

@Component
class AddContactTransform : Transformer<AddContactRequest,Contact> {
    override fun transform(source: AddContactRequest): Contact {
        return Contact(
            photo = source.photo,
            name = source.name,
            region = source.region,
            locality = source.locality,
            comment = source.comment,
            email = source.email,
            phone = source.phone
        )
    }
}