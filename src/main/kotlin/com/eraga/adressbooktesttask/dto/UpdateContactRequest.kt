package com.eraga.adressbooktesttask.dto

data class UpdateContactRequest(val name:String,
                                val region:String,
                                val locality:String,
                                val phone:String,
                                val email:String,
                                val photo:String,) {
}