package com.puzzlebench.cmk.domain.service

import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.cmk.domain.model.FullInfoCharacter
import io.reactivex.Single

interface CharacterServices {
    fun getCharacters(): Single<List<Character>>

    fun getSingleCharacter(id: Int) : Single<FullInfoCharacter>
}