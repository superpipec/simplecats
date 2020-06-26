package net.softandroid.data.auth

import net.softandroid.domain.auth.TokenRepository

const val API_KEY = "0799a510-8699-4ba7-a612-d9b4e6f0161e" // stub implementation

class BaseTokenRepository : TokenRepository {

    override fun getKey(): String = API_KEY
}