package com.eraga.adressbooktesttask.dto

class AddContactRequest(val name:String,
                        val region:String,
                        val locality:String,
                        val phone:Int,
                        val email:String,
                        val photo:String,
                        val comment:String)