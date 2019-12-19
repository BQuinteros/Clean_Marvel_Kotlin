package com.puzzlebench.cmk.domain.model

class FullInfoCharacter(
        override var id: Int,
        override var name: String,
        override var description: String,
        override var thumbnail: Thumbnail,
        var comics: Comics
) : Character(id, name, description, thumbnail) {

    override fun isEmpty() : Boolean {
        if (comics.avalible == null) {
            return true
        }
        return false
    }
}