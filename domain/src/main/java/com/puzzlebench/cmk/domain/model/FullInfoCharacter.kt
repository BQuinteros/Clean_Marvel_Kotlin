package com.puzzlebench.cmk.domain.model

class FullInfoCharacter(
        var id: Int,
        var name: String,
        var description: String,
        var thumbnail: Thumbnail,
        var comics: Comics
){

    fun isEmpty() : Boolean {
        if (comics.avalible == null) {
            return true
        }
        return false
    }
}