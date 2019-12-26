package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view

import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.adapter.CharacterAdapter
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.util.ONE
import com.puzzlebench.cmk.domain.model.Character
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.recycleView
import java.lang.ref.WeakReference

class CharacterView(activity: MainActivity) {
    private val activityRef = WeakReference(activity)
    private val SPAN_COUNT = ONE
    var adapter = CharacterAdapter { character -> showFragmentDialog(character) }

    fun init() {
        val activity = activityRef.get()
        activity?.let {
            activity.recycleView.layoutManager = GridLayoutManager(activity, SPAN_COUNT)
            activity.recycleView.adapter = adapter
            showLoading()
        }
    }

    fun showToastNoItemToShow() {
        val activity = activityRef.get()
        if (activity != null) {
            val message = activity.baseContext.resources.getString(R.string.message_no_items_to_show)
            activity.applicationContext.showToast(message)

        }
    }

    fun showToastNetworkError(error: String) {
        activityRef.get()?.applicationContext?.showToast(error)
    }

    fun hideLoading() {
        activityRef.get()?.progressBar?.visibility = View.GONE
    }

    fun showCharacters(characters: List<Character>) {
        adapter.data = characters
    }

    fun showLoading() {
        activityRef.get()?.progressBar?.visibility = View.VISIBLE
    }

    fun showFragmentDialog(character: Character) {
        val newFragment = CharacterFragment.newInstance(character, activityRef.get()!!)
        newFragment.show(activityRef.get()?.fragmentManager, "FRAGMENT_DIALOG")
    }
}
