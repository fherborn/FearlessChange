package de.thkoeln.colab.fearlesschange.ui.pattern.preview

import android.view.View
import de.thkoeln.colab.fearlesschange.R
import de.thkoeln.colab.fearlesschange.data.persistance.pattern.PatternInfo
import de.thkoeln.colab.fearlesschange.helper.extensions.getResourceId
import de.thkoeln.colab.fearlesschange.ui.LayoutAdapter
import kotlinx.android.synthetic.main.pattern_preview_fragment.view.*

class PatternPreviewAdapter(var patternClickedListener: (patternInfo: PatternInfo) -> Unit = {}) : LayoutAdapter<PatternInfo>(R.layout.pattern_preview_fragment) {
    override fun bind(view: View, value: PatternInfo) {
        with(value.pattern) {
            view.pattern_preview_title.text = title
            view.pattern_preview_summary.text = summary
            view.pattern_preview_image.setImageResource(view.context.getResourceId(pictureName, "drawable") ?: R.drawable.default_pattern_image)
            view.pattern_preview_favorite_icon.visibility = if (favorite) View.VISIBLE else View.INVISIBLE
            view.pattern_preview_notes_count.text = value.noteCount.toString()
            view.pattern_preview_card.setOnClickListener { patternClickedListener(value) }
        }
    }
}