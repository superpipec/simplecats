package net.softandroid.domain.common

/**
 * Use case exception handler declaration. Where generic type E - is possible errors of use case.
 * */
interface UseCaseErrorHandler<E> {

    /**
     * Handle use case exception.
     *
     * @param throwable catched exception
     *
     * @return use case error instance
     * */
    fun handle(throwable: Throwable): E?

}