package net.softandroid.data.rest.api.entities

import net.softandroid.domain.main.entity.Category
import net.softandroid.domain.main.entity.GetCatsItem

data class GetCatsEntity(
    override val breeds: Collection<Any>,
    override val categories: Collection<CategoryEntity>,
    override val height: Int,
    override val id: String,
    override val url: String,
    override val width: Int
) : GetCatsItem

data class CategoryEntity(override val id: Int, override val name: String) : Category

