package com.example.myapplication.model


data class marvelHero(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String?,
    val thumbnailUrl: String,

    val comicsAvailable: Int,
    val seriesAvailable: Int,
    val storiesAvailable: Int,
    val eventsAvailable: Int,

    val urls: Urls
) {
    data class Urls(
        val detail: String,
        val wiki: String,
        val comicLink: String,
    ) {

        fun getFirst(): String {
            return when {
                detail.isNotEmpty() -> detail
                wiki.isNotEmpty() -> wiki
                else -> comicLink
            }
        }
    }
}