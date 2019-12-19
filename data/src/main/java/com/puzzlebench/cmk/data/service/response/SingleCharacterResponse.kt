package com.puzzlebench.cmk.data.service.response

class SingleCharacterResponse (
        override var id: Int,
        override var name: String,
        override var description: String,
        override var thumbnail: ThumbnailResponse,
        var comics: ComicsResponse
) : CharacterResponse(id,name,description,thumbnail)