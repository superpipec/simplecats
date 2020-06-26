package net.softandroid.domain.favourites

import net.softandroid.domain.common.Result
import net.softandroid.domain.common.UseCaseErrorHandler
import net.softandroid.domain.favourites.entity.FavsCatsItem

class FavsCatsUseCase(
    private val repository: FavsCatsLocalRepository,
    private val errorHandler: UseCaseErrorHandler<FavsCatsError>
) {

    suspend fun saveCat(favsCatsItem: FavsCatsItem): Result<Unit, FavsCatsError> {
        return try {
            Result.Success(repository.save(favsCatsItem))
        } catch (throwable: Throwable) {
            errorHandler.handle(throwable)?.let {
                Result.Fail(it)
            } ?: throw throwable
        }

    }

    suspend fun getAllCats(): Result<Collection<FavsCatsItem>, FavsCatsError> {
        return try {
            Result.Success(repository.getAll())
        } catch (throwable: Throwable) {
            errorHandler.handle(throwable)?.let {
                Result.Fail(it)
            } ?: throw throwable
        }
    }
}