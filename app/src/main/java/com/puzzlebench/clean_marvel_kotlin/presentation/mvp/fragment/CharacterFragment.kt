package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharacterFragmentModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter.CharacterFragmentPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharacterFragmentView
import com.puzzlebench.clean_marvel_kotlin.presentation.util.HEIGHT
import com.puzzlebench.clean_marvel_kotlin.presentation.util.WIDTH
import com.puzzlebench.cmk.data.service.CharacterServicesImpl
import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.cmk.domain.usecase.GetSingleCharacterServiceUseCase

class CharacterFragment : DialogFragment() {

    private val getSingleCharacterServiceUseCase = GetSingleCharacterServiceUseCase(CharacterServicesImpl(), character.id)
    private val presenter = CharacterFragmentPresenter(CharacterFragmentView(mainActivity), CharacterFragmentModel(getSingleCharacterServiceUseCase))

    companion object {
        lateinit var character: Character
        private lateinit var mainActivity: MainActivity
        fun newInstance(character: Character, activity: MainActivity): CharacterFragment {
            this.character = character
            mainActivity = activity
            return CharacterFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        val window = dialog.window
        window?.setLayout(WIDTH.toInt(), HEIGHT.toInt())
        window?.setGravity(Gravity.CENTER)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_character, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        presenter.init(this)
        super.onViewCreated(view, savedInstanceState)
    }
}