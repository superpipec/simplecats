package net.softandroid.simplecats.base.main

import net.softandroid.domain.main.entity.GetCatsItem

interface MainNavigator {

    fun toFavourites()

    fun addToFavourites(getCatsItem: GetCatsItem)

    fun download(url: String)
}