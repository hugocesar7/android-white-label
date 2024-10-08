package com.example.androidwhitelabel.data.model

data class Country (
    val name: Name,
    val capital: List<String>?,
    val population: Long,
    val flags: Flags
)

data class Name(val common: String)

data class Flags(val png: String)