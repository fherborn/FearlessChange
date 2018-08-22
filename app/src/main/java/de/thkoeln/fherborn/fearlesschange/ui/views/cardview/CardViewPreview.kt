package de.thkoeln.fherborn.fearlesschange.ui.views.cardview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import de.thkoeln.fherborn.fearlesschange.R
import de.thkoeln.fherborn.fearlesschange.persistance.models.Card
import kotlinx.android.synthetic.main.card_preview.view.*


/**
 * Created by florianherborn on 30.07.18.
 */
class CardViewPreview : CardView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    override fun onCreateContentView(inflater: LayoutInflater, rootView: CardView, context: Context, attributeSet: AttributeSet?): View = inflater.inflate(R.layout.card_preview, rootView, false)

    var notesCount: Int = 0
        set(value) {
            field = value
            onNotesCountChanged(field)
        }

    private fun onNotesCountChanged(count: Int) {
        if (count > 0) {
            card_preview_notes_count.text = count.toString()
            card_preview_notes_count.visibility = VISIBLE
            card_preview_notes_icon.visibility = VISIBLE
        } else {
            card_preview_notes_count.visibility = GONE
            card_preview_notes_icon.visibility = GONE
        }
    }

    override fun onCardChanged(card: Card?) {
        /*
        card?.let {
            card_front_title?.text = it.title
            card_front_summary?.text = it.problem
            fav_icon.setImageResource(
                    when {
                        card.favorite -> R.drawable.ic_favorite_full_white
                        else -> R.drawable.ic_favorite_white
                    }
            )

            GlideApp.with(context)
                    .load(getResourceId(context, card.pictureName, "drawable"))
                    .placeholder(R.drawable.default_pattern_image)
                    .fitCenter()
                    .into(card_front_image)
        }
        */
    }

}