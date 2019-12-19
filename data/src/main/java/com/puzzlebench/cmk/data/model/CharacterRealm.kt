package com.puzzlebench.cmk.data.model

import io.realm.RealmObject

open class CharacterRealm(
        var id: Int? = null,
        var name: String? = null,
        var description: String? = null,
        var thumbnail: ThumbnailRealm? = null,
        var comics: ComicsRealm? = ComicsRealm(id)
) : RealmObject()