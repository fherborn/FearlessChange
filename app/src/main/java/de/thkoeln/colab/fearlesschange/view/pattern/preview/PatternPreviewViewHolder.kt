package de.thkoeln.colab.fearlesschange.view.pattern.preview

import android.view.View
import de.thkoeln.colab.fearlesschange.R
import de.thkoeln.colab.fearlesschange.core.getResourceId
import de.thkoeln.colab.fearlesschange.core.layout.LayoutViewHolder
import de.thkoeln.colab.fearlesschange.persistance.pattern.PatternInfo
import kotlinx.android.synthetic.main.pattern_preview.view.*


class PatternPreviewViewHolder(var patternClickedListener: (patternInfo: PatternInfo) -> Unit = {}) : LayoutViewHolder<PatternInfo>(R.layout.pattern_preview) {
    override fun bind(view: View, value: PatternInfo) {
        with(value.pattern) {
            val picture = loadImage(view, pictureName)
            view.pattern_preview_image.setImageResource(picture)
            view.pattern_preview_title.text = title
            view.pattern_preview_summary.text = summary
            view.pattern_preview_favorite_icon.visibility = if (favorite) View.VISIBLE else View.GONE
            view.pattern_preview_notes_count.text = value.noteCount.toString()
            view.pattern_preview_notes_count.visibility = if (value.noteCount > 0) View.VISIBLE else View.GONE
            view.pattern_preview_notes_icon.visibility = if (value.noteCount > 0) View.VISIBLE else View.GONE
            view.pattern_preview_card.setOnClickListener { patternClickedListener(value) }
        }
    }

    fun loadImage(view: View, pictureName: String): Int =
            view.context?.getResourceId(pictureName, "drawable") ?: R.drawable.default_pattern_image

}