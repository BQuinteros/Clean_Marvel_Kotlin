package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.view

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.GridLayoutManager
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.adapter.CharacterAdapter
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.util.ONE
import com.puzzlebench.cmk.domain.model.Character
import kotlinx.android.synthetic.main.activity_main.floatingActionButton
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.recycleView
import kotlinx.android.synthetic.main.activity_main.secondFloatingActionButton
import kotlinx.android.synthetic.main.activity_main.thirdFloatingActionButton
import java.lang.ref.WeakReference

class CharacterView(activity: MainActivity) {
    private val activityRef = WeakReference(activity)
    private val SPAN_COUNT = ONE
    var adapter = CharacterAdapter { character -> showFragmentDialog(character) }

    fun init() {
        activityRef.get()?.let {
            it.recycleView.layoutManager = GridLayoutManager(it, SPAN_COUNT)
            it.recycleView.adapter = adapter
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
        activityRef.get()?.progressBar?.visibility = GONE
    }

    fun showCharacters(characters: List<Character>) {
        adapter.data = characters
    }

    fun showLoading() {
        activityRef.get()?.progressBar?.visibility = VISIBLE
    }

    fun showFragmentDialog(character: Character) {
        activityRef.get()?.let {
            val newFragment = CharacterFragment.newInstance(character, it)
            newFragment.show(it.fragmentManager, "FRAGMENT_DIALOG")
        }
    }

    fun showIcon() {
        activityRef.get()?.floatingActionButton?.visibility = VISIBLE
        activityRef.get()?.secondFloatingActionButton?.visibility = VISIBLE
        activityRef.get()?.thirdFloatingActionButton?.visibility = VISIBLE
    }

    fun hideIcon() {
        activityRef.get()?.floatingActionButton?.visibility = GONE
        activityRef.get()?.secondFloatingActionButton?.visibility = GONE
        activityRef.get()?.thirdFloatingActionButton?.visibility = GONE
    }
}

