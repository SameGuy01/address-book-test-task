package com.eraga.adressbooktesttask.transform

interface Transformer<A,B> {
    fun transform(source:A):B
}