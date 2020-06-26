package net.softandroid.data.main

import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import net.softandroid.data.rest.api.CatsApi
import net.softandroid.data.rest.api.entities.GetCatsEntity
import net.softandroid.domain.auth.TokenRepository
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.ArgumentMatchers.anyString
import retrofit2.Response

private const val TOKEN = "123"

internal class ApiGetCatsListRepositoryTest {
    private val response = mock<List<GetCatsEntity>>()


    private val tokenRepository = mock<TokenRepository> {
        on { getKey() } doReturn TOKEN
    }
    private val catsApi = mock<CatsApi>() {
        onBlocking { getList(anyString(), anyOrNull(), anyOrNull()) } doReturn Response.success(
            response
        )
    }

    private val repository = ApiGetCatsListRepository(tokenRepository, catsApi)

    @Test
    fun `delegate get to repository`() {
        runBlocking {
            repository.getCatsList(null)
            verify(catsApi).getList(tokenRepository.getKey(), null)
        }
    }

    @Test
    fun `return list result from api when get`() {
        runBlocking {
            assertEquals(
                response,
                repository.getCatsList(null)
            )
        }
    }
}