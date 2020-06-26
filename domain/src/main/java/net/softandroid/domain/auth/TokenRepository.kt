package net.softandroid.domain.auth

interface TokenRepository {

    fun getKey(): String
}