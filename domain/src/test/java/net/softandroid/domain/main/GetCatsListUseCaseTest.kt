package net.softandroid.domain.main

import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import net.softandroid.domain.common.UseCaseErrorHandler
import net.softandroid.domain.common.mapFailure
import net.softandroid.domain.main.entity.GetCatsItem
import org.junit.Assert
import org.junit.Test

private const val PAGE = 1
private const val BAD_PAGE = 122222

internal class GetCatsListUseCaseTest {
    private val items = mock<Collection<GetCatsItem>>()

    private val repository = mock<GetCatsListRepository> {
        onBlocking { getCatsList(anyOrNull()) } doReturn items
        onBlocking { getCatsList(BAD_PAGE) } doReturn emptyList()
    }

    private val errorHandler = mock<UseCaseErrorHandler<GetCatsListError>> {
        on { handle(any()) } doReturn GetCatsListError.NetworkError
    }

    private val interactor = GetCatsListUseCase(repository, errorHandler)

    @Test
    fun `delegate get items to repository`() {
        runBlocking {
            interactor.getCatsListImages(PAGE)
            verify(repository).getCatsList(PAGE)
        }
    }

    @Test
    fun `handle exception when repository failed`() {
        runBlocking {
            val throwable = mock<RuntimeException>()
            whenever(
                repository.getCatsList(PAGE)
            ).thenThrow(throwable)
            interactor.getCatsListImages(PAGE)
            verify(errorHandler).handle(throwable)
        }
    }

    @Test
    fun `return NoResultsFound when subject is null`() {
        runBlocking {
            val result = interactor.getCatsListImages(BAD_PAGE)
            Assert.assertEquals("Bad result is success", result.isSuccess, false)
            result.mapFailure {
                Assert.assertEquals("Error", it, GetCatsListError.NoResultsFound)
            }
        }
    }
}