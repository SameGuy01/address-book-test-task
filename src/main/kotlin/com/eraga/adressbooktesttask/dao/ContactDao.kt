package com.eraga.adressbooktesttask.dao

import com.eraga.adressbooktesttask.domain.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactDao: JpaRepository<Contact,Long>