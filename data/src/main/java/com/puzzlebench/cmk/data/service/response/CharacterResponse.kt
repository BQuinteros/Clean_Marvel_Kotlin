package com.puzzlebench.cmk.data.service.response

class CharacterResponse(
        var id: Int,
        var name: String,
        var description: String,
        var thumbnail: ThumbnailResponse?
)