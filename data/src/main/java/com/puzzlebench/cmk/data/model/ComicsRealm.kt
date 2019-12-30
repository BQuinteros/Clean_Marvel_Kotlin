package com.puzzlebench.cmk.data.model

import io.realm.RealmObject

open class ComicsRealm (
        var id: Int = DEFAULT_INT,
        var available: Int = DEFAULT_INT,
        var collectionURI: String = EMPTY_STRING
): RealmObject()