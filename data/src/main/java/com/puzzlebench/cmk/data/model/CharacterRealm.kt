package com.puzzlebench.cmk.data.model

import io.realm.RealmObject

const val EMPTY_STRING: String = ""
const val DEFAULT_INT: Int = 0

open class CharacterRealm(
        var id: Int = DEFAULT_INT,
        var name: String = EMPTY_STRING,
        var description: String = EMPTY_STRING,
        var thumbnail: ThumbnailRealm? = ThumbnailRealm(),
        var comics: ComicsRealm? = ComicsRealm(id)
) : RealmObject()