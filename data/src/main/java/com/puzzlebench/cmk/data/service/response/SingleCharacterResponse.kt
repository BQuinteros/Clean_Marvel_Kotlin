package com.puzzlebench.cmk.data.service.response

import com.puzzlebench.cmk.domain.model.DEFAULT_INT
import com.puzzlebench.cmk.domain.model.EMPTY_STRING

class SingleCharacterResponse (
        var id: Int = DEFAULT_INT,
        var name: String = EMPTY_STRING,
        var description: String = EMPTY_STRING,
        var thumbnail: ThumbnailResponse,
        var comics: ComicsResponse
)