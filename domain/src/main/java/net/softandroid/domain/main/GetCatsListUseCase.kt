package net.softandroid.domain.main

import net.softandroid.domain.common.Result
import net.softandroid.domain.common.UseCaseErrorHandler
import net.softandroid.domain.main.entity.GetCatsItem

class GetCatsListUseCase(
    private val repository: GetCatsListRepository,
    private val errorHandler: UseCaseErrorHandler<GetCatsListError>
) {

    suspend fun getCatsListImages(
        page: Int?
    ): Result<Collection<GetCatsItem>, GetCatsListError> {


        return try {
            val result = repository.getCatsList(page)
            if (result.isEmpty()) {
                return Result.Fail(GetCatsListError.NoResultsFound)
            }

            Result.Success(result)
        } catch (throwable: Throwable) {
            errorHandler.handle(throwable)?.let {
                Result.Fail(it)
            } ?: throw throwable
        }
    }
}