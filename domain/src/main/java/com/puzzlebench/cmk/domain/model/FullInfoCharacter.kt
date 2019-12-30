package com.puzzlebench.cmk.domain.model

const val DEFAULT_INT: Int = 0
const val EMPTY_STRING: String = ""

class FullInfoCharacter(
        var id: Int = DEFAULT_INT,
        var name: String = EMPTY_STRING,
        var description: String = EMPTY_STRING,
        var thumbnail: Thumbnail,
        var comics: Comics
) {

    fun isEmpty(): Boolean {
        if (comics.avalible == DEFAULT_INT) {
            return true
        }
        return false
    }
}