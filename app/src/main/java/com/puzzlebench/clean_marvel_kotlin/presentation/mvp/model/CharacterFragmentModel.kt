package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model

import com.puzzlebench.cmk.domain.model.FullInfoCharacter
import com.puzzlebench.cmk.domain.usecase.GetSingleCharacterServiceUseCase
import io.reactivex.Single

open class CharacterFragmentModel (val characterServiceUseCase: GetSingleCharacterServiceUseCase) {
    open fun getCharacterServiceUseCase(): Single<FullInfoCharacter> = characterServiceUseCase.invoke()
}