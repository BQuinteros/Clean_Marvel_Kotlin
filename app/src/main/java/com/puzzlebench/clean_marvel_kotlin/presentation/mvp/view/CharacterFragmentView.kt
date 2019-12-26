package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view

import android.view.View
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.getImageByUrl
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.util.DOT
import com.puzzlebench.cmk.domain.model.FullInfoCharacter
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.fragment_character.description_character
import kotlinx.android.synthetic.main.fragment_character.image_thumbnail
import kotlinx.android.synthetic.main.fragment_character.text_character
import java.lang.ref.WeakReference

class CharacterFragmentView(activity: MainActivity) {

    private val activityRef = WeakReference(activity)

    fun showFragmentDialog(characterFragment: CharacterFragment, character: FullInfoCharacter) {
        val url = "${character.thumbnail.path}$DOT${character.thumbnail.extension}"
        characterFragment.text_character.text = character.name
        characterFragment.description_character.text = character.description
        characterFragment.image_thumbnail.getImageByUrl(url)
    }

    fun showToastNoItemToShow() {
        val activity = activityRef.get()
        val message = activity?.baseContext?.resources?.getString(R.string.message_no_items_to_show)
        activity?.applicationContext?.showToast(message as String)
    }

    fun showToastNetworkError(error: String) {
        activityRef.get()?.applicationContext?.showToast(error)
    }

    fun hideLoading() {
        activityRef.get()?.let { it.progressBar.visibility = View.GONE }
        activityRef.get()?.progressBar?.visibility = View.GONE
    }
}
