package com.eraga.adressbooktesttask.domain

import javax.persistence.*

@Entity
@Table(name="contact")
@SecondaryTable(name = "contact_information")
data class Contact(
    @Id
    @SequenceGenerator(name=ADDRESS_BOOK_SEQUENCE,sequenceName = ADDRESS_BOOK_SEQUENCE, initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ADDRESS_BOOK_SEQUENCE)
    val id:Long=1,

    var name:String="",

    var region:String="",

    var locality:String?=null,

    var photo:String?=null,

    @Column(table="contact_information")
    var email:String,

    @Column(table="contact_information")
    var phone:Int,

    var comment:String?=null) {

    companion object {
        const val ADDRESS_BOOK_SEQUENCE: String = ""
    }
}