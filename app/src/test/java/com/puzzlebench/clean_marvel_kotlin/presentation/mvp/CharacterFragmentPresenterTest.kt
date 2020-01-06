package com.puzzlebench.clean_marvel_kotlin.fragment.mvp

import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharacterFragmentModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter.CharacterFragmentPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharacterFragmentView
import com.puzzlebench.cmk.domain.model.Comics
import com.puzzlebench.cmk.domain.model.FullInfoCharacter
import com.puzzlebench.cmk.domain.model.Thumbnail
import com.puzzlebench.cmk.domain.usecase.GetSingleCharacterServiceUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

const val ZERO_VALUE = 0
const val TEN_VALUE = 10
const val EMPTY_VALUE = ""

class CharacterFragmentPresenterTest {

    @Mock
    private lateinit var view: CharacterFragmentView
    @Mock
    private lateinit var charactersFragment: CharacterFragment
    @Mock
    private lateinit var getSingleCharacterServiceUseCase: GetSingleCharacterServiceUseCase

    private lateinit var model: CharacterFragmentModel
    private lateinit var presenter: CharacterFragmentPresenter

    companion object {

        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            val immediate = object : Scheduler() {
                internal var noDelay = ZERO_VALUE

                override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                    return super.scheduleDirect(run, noDelay.toLong(), unit) // Prevents StackOverflowErrors when scheduling with a delay
                }

                override fun createWorker(): Scheduler.Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }
            // These avoid ExceptionInInitializerError when testing methods that contains RxJava Schedulers
            RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
        }

        private const val SIZE = TEN_VALUE
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        model = CharacterFragmentModel(getSingleCharacterServiceUseCase)
        presenter = CharacterFragmentPresenter(view, model)
    }

    @Test
    fun serviceResponseWithError() {
        `when`(getSingleCharacterServiceUseCase.invoke()).thenReturn(Single.error(Exception(EMPTY_VALUE)))
        presenter.init(charactersFragment)
        verify(getSingleCharacterServiceUseCase).invoke()
        verify(view).showToastNetworkError(EMPTY_VALUE)
        verify(view).hideLoading()
    }

    @Test
    fun serviceResponseWithItemToShow() {
        val hero = getHero(TEN_VALUE, TEN_VALUE, EMPTY_VALUE)
        val observable = Single.just(hero)
        `when`(getSingleCharacterServiceUseCase.invoke()).thenReturn(observable)
        presenter.init(charactersFragment)
        verify(getSingleCharacterServiceUseCase).invoke()
        verify(view).showFragmentDialog(charactersFragment, hero)
        verify(view).hideLoading()
    }

    @Test
    fun serviceResponseWithoutItemToShow() {
        val hero = getHero(ZERO_VALUE, ZERO_VALUE, EMPTY_VALUE)
        `when`(getSingleCharacterServiceUseCase.invoke()).thenReturn(Single.just(hero))
        presenter.init(charactersFragment)
        verify(getSingleCharacterServiceUseCase).invoke()
        verify(view).showToastNoItemToShow()
        verify(view).hideLoading()
    }

    private fun getHero(id: Int, available: Int, collection: String): FullInfoCharacter {
        val comic = Comics(id, available, collection)
        val thumbnail = Thumbnail(EMPTY_VALUE, EMPTY_VALUE)
        return FullInfoCharacter(id = ZERO_VALUE, comics = comic, thumbnail = thumbnail)
    }
}
