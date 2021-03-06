package com.puzzlebench.clean_marvel_kotlin.presentation

import android.os.Bundle
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter.CharacterPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharacterView
import com.puzzlebench.cmk.data.mapper.repository.CharacterMapperRepository
import com.puzzlebench.cmk.data.repository.CharacterDataRepository
import com.puzzlebench.cmk.data.repository.source.CharacterDataSourceImpl
import com.puzzlebench.cmk.data.service.CharacterServicesImpl
import com.puzzlebench.cmk.domain.usecase.GetCharacterRepositoryUseCase
import com.puzzlebench.cmk.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.cmk.domain.usecase.SaveCharacterRepositoryUseCase
import kotlinx.android.synthetic.main.activity_main.floatingActionButton
import kotlinx.android.synthetic.main.activity_main.secondFloatingActionButton
import kotlinx.android.synthetic.main.activity_main.thirdFloatingActionButton

class MainActivity : BaseRxActivity() {

    private val getCharacterServiceUseCase = GetCharacterServiceUseCase(CharacterServicesImpl())
    private val getCharacterRepositoryUseCase = GetCharacterRepositoryUseCase(CharacterDataRepository(CharacterDataSourceImpl(), CharacterMapperRepository()))
    private val saveCharacterRepositoryUseCase = SaveCharacterRepositoryUseCase(CharacterDataRepository(CharacterDataSourceImpl(), CharacterMapperRepository()))

    private val presenter = CharacterPresenter(CharacterView(this),
            getCharacterServiceUseCase,
            getCharacterRepositoryUseCase,
            saveCharacterRepositoryUseCase,
            subscriptions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listenerButtonClick()
        presenter.init()
    }

    fun listenerButtonClick() {
        floatingActionButton.setOnClickListener { presenter.refreshCharacterPresenter() }
        secondFloatingActionButton.setOnClickListener { presenter.refreshCharactersDataBase() }
        thirdFloatingActionButton.setOnClickListener { presenter.deleteListOfCharacters() }
    }
}
