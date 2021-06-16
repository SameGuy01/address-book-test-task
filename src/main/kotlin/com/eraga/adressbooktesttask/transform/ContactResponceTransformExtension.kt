package com.eraga.adressbooktesttask.transform

import com.eraga.adressbooktesttask.domain.Contact
import com.eraga.adressbooktesttask.dto.ContactResponse

fun Contact?.toContactResponse(): ContactResponse {
    return ContactResponse(
        id=this?.id ?: 1L,
        name = "${this?.name}",
        phone = this?.phone!!
    )
}