package com.puzzlebench.cmk.data.model

import io.realm.RealmObject

open class ComicsRealm (
        var id: Int? = null,
        var available: Int? = null,
        var collectionURI: String? = null
): RealmObject()