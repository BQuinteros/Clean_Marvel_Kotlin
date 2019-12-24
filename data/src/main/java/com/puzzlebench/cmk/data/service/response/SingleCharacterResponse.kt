package com.puzzlebench.cmk.data.service.response

class SingleCharacterResponse (
        var id: Int,
        var name: String,
        var description: String,
        var thumbnail: ThumbnailResponse,
        var comics: ComicsResponse
)