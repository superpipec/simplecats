package net.softandroid.domain.favourites

import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import net.softandroid.domain.common.UseCaseErrorHandler
import net.softandroid.domain.favourites.entity.FavsCatsItem
import org.junit.Test

private val FAV_CAT = mock<FavsCatsItem>()

internal class FavsCatsUseCaseTest {

    private val favsCatList = mock<Collection<FavsCatsItem>>()

    private val repository = mock<FavsCatsLocalRepository> {
        onBlocking { getAll() } doReturn favsCatList
        onBlocking { save(any()) } doReturn Unit
    }

    private val errorHandler = mock<UseCaseErrorHandler<FavsCatsError>> {
        on { handle(any()) } doReturn FavsCatsError.StubError
    }


    private val interactor = FavsCatsUseCase(repository, errorHandler)

    @Test
    fun `delegate get favs to repository`() {
        runBlocking {
            interactor.getAllCats()
            verify(repository).getAll()
        }
    }

    @Test
    fun `delegate save fav to repository`() {
        runBlocking {
            interactor.saveCat(FAV_CAT)
            verify(repository).save(FAV_CAT)
        }
    }

    @Test
    fun `handle exception when repository failed`() {
        runBlocking {
            val throwable = mock<RuntimeException>()
            whenever(
                repository.getAll()
            ).thenThrow(throwable)
            interactor.getAllCats()
            verify(errorHandler).handle(throwable)
        }
    }
}