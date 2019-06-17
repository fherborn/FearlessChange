package de.thkoeln.colab.fearlesschange.ui.customs.card

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import de.thkoeln.colab.fearlesschange.R
import de.thkoeln.colab.fearlesschange.ui.adapter.AdaptableView
import de.thkoeln.colab.fearlesschange.ui.adapter.SingleViewAdapter

/**
 * Created by Florian on 23.08.2018.
 */
class PatternCardPreview @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr), AdaptableView<PatternCardPreview> {

    private val delegation = PatternCardDelegation(this)
    var title by delegation.updateTitle(R.id.pattern_preview_title)
    var summary by delegation.updateSummary(R.id.pattern_preview_summary)
    var imageId by delegation.updateCardImage(R.id.pattern_preview_image)
    var favorite by delegation.updateFavoriteIcon(R.id.pattern_preview_favorite_icon)
    var noteCount by delegation.updateNotesCount(R.id.pattern_preview_notes_count, R.id.pattern_preview_notes_icon)
    var background by delegation.updateCardBackground(R.id.pattern_preview_content_wrapper)
    var contentBackground by delegation.updateCardContentBackground(R.id.pattern_preview_content)
    var onCardClickedListener by delegation.updateClickListener(R.id.pattern_preview_card)

    init {
        View.inflate(context, R.layout.pattern_card_preview, this)
        context?.obtainStyledAttributes(attrs, R.styleable.PatternCardPreview, defStyleAttr, 0)?.apply {
            try {
                favorite = getBoolean(R.styleable.PatternCardPreview_PatternCardFavorite, delegation.favoriteDefault)
                title = getString(R.styleable.PatternCardPreview_PatternCardTitle) ?: delegation.titleDefault
                summary = getString(R.styleable.PatternCardPreview_PatternCardSummary) ?: delegation.summaryDefault
                imageId = getInt(R.styleable.PatternCardPreview_PatternCardImage, delegation.imageDefault)
                noteCount = getInt(R.styleable.PatternCardPreview_PatternCardNoteCount, delegation.noteCountDefault)
                background = getInt(R.styleable.PatternCardPreview_PatternCardBackground, delegation.backgroundDefault)
                contentBackground = getInt(R.styleable.PatternCardPreview_PatternCardContentBackground, delegation.contentBackgroundDefault)
            } finally {
                recycle()
            }
        }
    }

    override fun <T> setAdapter(adapter: SingleViewAdapter<T, PatternCardPreview>) {
        adapter.registerView(this)
    }

}