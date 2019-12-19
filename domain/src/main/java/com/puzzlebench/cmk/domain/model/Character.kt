package com.puzzlebench.cmk.domain.model

open class Character(
        open val id: Int,
        open val name: String,
        open val description: String,
        open val thumbnail: Thumbnail
) {
    open fun isEmpty() : Boolean {
        if ( name.isEmpty() ) {
            return true
        }
        return false
    }
}
