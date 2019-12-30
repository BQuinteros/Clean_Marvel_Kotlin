package com.puzzlebench.cmk.data.model

import io.realm.RealmObject

open class ThumbnailRealm(var path: String = EMPTY_STRING, var extension: String = EMPTY_STRING) : RealmObject()