package net.softandroid.domain.main

sealed class GetCatsListError {

    object NetworkError : GetCatsListError()

    object NoResultsFound : GetCatsListError()
}