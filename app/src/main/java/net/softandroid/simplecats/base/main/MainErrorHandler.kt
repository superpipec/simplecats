package net.softandroid.simplecats.base.main

import net.softandroid.domain.main.GetCatsListError
import net.softandroid.simplecats.R

class MainErrorHandler(
    private val vm: MainViewModel
) {

    fun handle(error: GetCatsListError) = when (error) {
        GetCatsListError.NetworkError -> vm.textErrorRes.value =
            R.string.common_network_error
        GetCatsListError.NoResultsFound -> vm.textErrorRes.value =
            R.string.common_text_no_result_error
    }
}