package com.puzzlebench.cmk.domain.model

open class Character(
        open val id: Int,
        open val name: String,
        open val description: String,
        open val thumbnail: Thumbnail?
)
