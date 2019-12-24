package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view

import android.view.View
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterFragment
import kotlinx.android.synthetic.main.activity_main.progressBar
import java.lang.ref.WeakReference

class CharacterFragmentView(activity: MainActivity) {

    private val activityRef = WeakReference(activity)

    fun showFragmentDialog(characterFragment: CharacterFragment) {
        val fragmentManager = activityRef.get()?.fragmentManager
        characterFragment.show(fragmentManager, "FragmentDialog")
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
        activityRef.get()!!.progressBar.visibility = View.GONE
    }
}
