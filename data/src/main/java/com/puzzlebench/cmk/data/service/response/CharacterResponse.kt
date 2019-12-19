package com.puzzlebench.cmk.data.service.response

open class CharacterResponse(
        open var id: Int,
        open var name: String,
        open var description: String,
        open var thumbnail: ThumbnailResponse
)