package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter

import com.puzzlebench.clean_marvel_kotlin.presentation.base.Presenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharacterFragmentModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharacterFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterFragmentPresenter(override val view: CharacterFragmentView, private val model: CharacterFragmentModel)
    : Presenter<CharacterFragmentView>(view){

    fun init(characterFragment: CharacterFragment) {
        requestGetCharacters(characterFragment)
    }

    private fun requestGetCharacters(characterFragment: CharacterFragment) {
        model.getCharacterServiceUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ characters ->
                    if (characters.isEmpty()) {
                        view.showToastNoItemToShow()
                    } else {
                        view.showFragmentDialog(characterFragment)
                    }
                }, { e ->
                    view.showToastNetworkError(e.message.toString())
                })
    }
}