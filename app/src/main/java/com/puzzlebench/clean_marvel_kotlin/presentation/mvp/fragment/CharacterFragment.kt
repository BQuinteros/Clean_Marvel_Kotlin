package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.model.CharacterFragmentModel
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.presenter.CharacterFragmentPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view.CharacterFragmentView
import com.puzzlebench.cmk.data.service.CharacterServicesImpl
import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.cmk.domain.usecase.GetSingleCharacterServiceUseCase

class CharacterFragment : DialogFragment(){

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

    fun init() {
        presenter.init(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_character,container)
        val textView = view?.findViewById<TextView>(R.id.text_character)
        textView?.text = "HI"
        return view
    }
}