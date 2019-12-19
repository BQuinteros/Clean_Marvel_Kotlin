package com.puzzlebench.cmk.domain.usecase

import com.puzzlebench.cmk.domain.model.FullInfoCharacter
import com.puzzlebench.cmk.domain.service.CharacterServices
import io.reactivex.Single

open class GetSingleCharacterServiceUseCase constructor(private val characterServiceImp: CharacterServices, private val id: Int) {

    open operator fun invoke(): Single<FullInfoCharacter> = characterServiceImp.getSingleCharacter(id)
}