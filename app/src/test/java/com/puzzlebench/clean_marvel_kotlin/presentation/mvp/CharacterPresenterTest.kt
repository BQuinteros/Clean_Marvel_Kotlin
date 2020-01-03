package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter.CharacterPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharacterView
import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.cmk.domain.repository.CharacterRepository
import com.puzzlebench.cmk.domain.service.CharacterServices
import com.puzzlebench.cmk.domain.usecase.GetCharacterRepositoryUseCase
import com.puzzlebench.cmk.domain.usecase.GetCharacterServiceUseCase
import com.puzzlebench.cmk.domain.usecase.SaveCharacterRepositoryUseCase
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class CharacterPresenterTest {

    private var view = mock(CharacterView::class.java)
    private var characterServiceImp = mock(CharacterServices::class.java)
    private var characterRepository = mock(CharacterRepository::class.java)

    private lateinit var characterPresenter: CharacterPresenter
    @Mock
    private lateinit var getCharacterServiceUseCase: GetCharacterServiceUseCase
    @Mock
    private lateinit var getCharacterRepositoryUseCase: GetCharacterRepositoryUseCase
    @Mock
    private lateinit var saveCharacterRepositoryUseCase: SaveCharacterRepositoryUseCase

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        getCharacterServiceUseCase = GetCharacterServiceUseCase(characterServiceImp)
        getCharacterRepositoryUseCase = GetCharacterRepositoryUseCase(characterRepository)
        saveCharacterRepositoryUseCase = SaveCharacterRepositoryUseCase(characterRepository)
        val subscriptions = mock(CompositeDisposable::class.java)
        characterPresenter = CharacterPresenter(view,
                getCharacterServiceUseCase,
                getCharacterRepositoryUseCase,
                saveCharacterRepositoryUseCase,
                subscriptions)
    }

    @Test
    fun init() {
        val itemsCharacters = listOf(1..5).map {
            mock(Character::class.java)
        }
        Mockito.`when`(getCharacterRepositoryUseCase.invoke()).thenReturn(itemsCharacters)
        characterPresenter.init()
        verify(view).init()
        verify(view).hideLoading()
        verify(view).showCharacters(itemsCharacters)
    }

    @Test
    fun reposeWithError() {
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(Single.error(Exception("")))
        characterPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCharacters()
        verify(view).hideLoading()
        verify(view).showToastNetworkError("")
    }

    @Test
    fun reposeWithItemToShow() {
        val itemsCharacters = listOf(1..5).map {
            mock(Character::class.java)
        }
        val observable = Single.just(itemsCharacters)
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(observable)
        characterPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCharacters()
        verify(view).hideLoading()
        verify(view).showCharacters(itemsCharacters)
    }

    @Test
    fun reposeWithoutItemToShow() {
        val itemsCharacters = emptyList<Character>()
        val observable = Single.just(itemsCharacters)
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(observable)
        characterPresenter.init()
        verify(view).init()
        verify(characterServiceImp).getCharacters()
    }

    @Test
    fun refreshCharacterPresenterReposeWithError() {
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(Single.error(Exception("")))
        val itemsCharacters = emptyList<Character>()
        characterPresenter.refreshCharacterPresenter()
        verify(view).showLoading()
        verify(view).hideIcon()
        verify(view).showCharacters(itemsCharacters)
        verify(characterServiceImp).getCharacters()
        verify(view, times(2)).hideLoading()
        verify(view).showToastNetworkError("")
    }

    @Test
    fun refreshCharacterPresenterReposeWithItemToShow() {
        val items = emptyList<Character>()
        val itemsCharacters = listOf(1..5).map {
            mock(Character::class.java)
        }
        val observable = Single.just(itemsCharacters)
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(observable)
        characterPresenter.refreshCharacterPresenter()
        verify(view).showLoading()
        verify(view).hideIcon()
        verify(view).showCharacters(items)
        verify(characterServiceImp).getCharacters()
        verify(characterRepository).save(itemsCharacters)
    }

    @Test
    fun refreshCharacterPresenterReposeWithoutItemToShow() {
        val itemsCharacters = emptyList<Character>()
        val observable = Single.just(itemsCharacters)
        Mockito.`when`(getCharacterServiceUseCase.invoke()).thenReturn(observable)
        characterPresenter.refreshCharacterPresenter()
        verify(view).showLoading()
        verify(view).hideIcon()
        verify(view).showCharacters(itemsCharacters)
        verify(characterServiceImp).getCharacters()
    }

    @Test
    fun refreshCharacterDataBaseNotEmptyTest() {
        val itemsCharacters = listOf(1..5).map {
            mock(Character::class.java)
        }
        Mockito.`when`(getCharacterRepositoryUseCase.invoke()).thenReturn(itemsCharacters)
        characterPresenter.refreshCharactersDataBase()
        verify(view).showCharacters(itemsCharacters)
        verify(view).showLoading()
        verify(view).hideIcon()
        verify(characterRepository).getAll()
        verify(view).showCharacters(itemsCharacters)
        verify(view).hideLoading()
        verify(view).showIcon()
    }

    @Test
    fun refreshCharacterDataBaseIsEmptyTest() {
        val itemsCharacters = emptyList<Character>()
        Mockito.`when`(getCharacterRepositoryUseCase.invoke()).thenReturn(itemsCharacters)
        characterPresenter.refreshCharactersDataBase()
        verify(view).showCharacters(itemsCharacters)
        verify(view).showLoading()
        verify(view).hideIcon()
        verify(characterRepository).getAll()
        verify(view).showCharacters(itemsCharacters)
        verify(view).hideLoading()
        verify(view).showIcon()
    }

    @Test
    fun deleteListOfCharactersTest() {
        val itemsCharacters = emptyList<Character>()
        characterPresenter.deleteListOfCharacters()
        verify(view).showCharacters(itemsCharacters)
    }
}