package net.softandroid.domain.main.entity


interface GetCatsItem {
    val breeds: Collection<Any> // big model, skip for speed developing due not required
    val categories: Collection<Category>
    val height: Int
    val id: String
    val url: String
    val width: Int
}

interface Category {
    val id: Int
    val name: String
}