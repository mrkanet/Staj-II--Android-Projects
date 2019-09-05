package com.example.retrofit

data class Items(
    val name: String, //project name
    val owner: Owner, //who made it
    val html_url: String, //project link github.com/owner[login]/name
    val description: String, //project description
    val stargazers_count: Int, //star count
    val language: String //which language did it written
)