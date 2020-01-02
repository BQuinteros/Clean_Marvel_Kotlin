package com.puzzlebench.clean_marvel_kotlin.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.getImageByUrl
import com.puzzlebench.clean_marvel_kotlin.presentation.listener.CharacterListener
import com.puzzlebench.clean_marvel_kotlin.presentation.util.DOT
import com.puzzlebench.cmk.domain.model.Character
import kotlinx.android.synthetic.main.character_cards_layout.view.image_thumbnail
import kotlinx.android.synthetic.main.character_cards_layout.view.tv_item

class CharactersAdapterViewHolder(view: View, val listener: CharacterListener) : RecyclerView.ViewHolder(view) {

    fun bind(item: Character) = with(itemView) {
        tv_item.text = item.name
        val string = "${item.thumbnail?.path}$DOT${item.thumbnail?.extension}"
        image_thumbnail.getImageByUrl(string)
        setOnClickListener { listener(item) }
    }
}